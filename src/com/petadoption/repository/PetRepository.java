package com.petadoption.repository;

import com.petadoption.exception.InvalidInputException;
import com.petadoption.model.Cat;
import com.petadoption.model.Dog;
import com.petadoption.model.Pet;
import com.petadoption.model.Shelter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepository implements IPetRepository {
    private final DataSource dataSource;

    public PetRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean create(Pet pet) {
        String sql = "INSERT INTO pets (name, type, age, adopted, shelter_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getType());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.getAdopted());

            if (pet.getShelter() != null && pet.getShelter().getId() > 0) {
                pstmt.setInt(5, pet.getShelter().getId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw mapSqlException(e, pet.getName());
        }
    }

    @Override
    public boolean updateByName(String name, Pet pet) {
        String sql = "UPDATE pets SET name = ?, type = ?, age = ?, adopted = ?, shelter_id = ? " +
                "WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getType());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.getAdopted());

            if (pet.getShelter() != null && pet.getShelter().getId() > 0) {
                pstmt.setInt(5, pet.getShelter().getId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setString(6, name);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw mapSqlException(e, pet.getName());
        }
    }

    @Override
    public boolean deleteByName(String name) {
        String sql = "DELETE FROM pets WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw mapSqlException(e, name);
        }
    }

    @Override
    public Pet findByName(String name) {
        String sql = "SELECT p.name, p.type, p.age, p.adopted, " +
                "s.id as shelter_id, s.name as shelter_name, s.location, s.rating " +
                "FROM pets p LEFT JOIN shelters s ON p.shelter_id = s.id " +
                "WHERE LOWER(p.name) = LOWER(?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while reading from the database. " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> dbPets = new ArrayList<>();
        String sql = "SELECT p.name, p.type, p.age, p.adopted, s.name as shelter_name, s.location, s.rating " +
                ", s.id as shelter_id FROM pets p LEFT JOIN shelters s ON p.shelter_id = s.id";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dbPets.add(mapRowToPet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while reading from the database. " + e.getMessage());
        }
        return dbPets;
    }

    private Pet mapRowToPet(ResultSet rs) throws SQLException {
        Shelter shelter = null;
        int shelterId = rs.getInt("shelter_id");
        if (!rs.wasNull()) {
            shelter = new Shelter(
                    rs.getString("shelter_name"),
                    rs.getString("location"),
                    rs.getDouble("rating")
            );
            shelter.setId(shelterId);
        }

        String petType = rs.getString("type");
        if ("Cat".equalsIgnoreCase(petType)) {
            return new Cat(
                    rs.getString("name"),
                    petType,
                    rs.getInt("age"),
                    rs.getBoolean("adopted"),
                    shelter
            );
        }
        return new Dog(
                rs.getString("name"),
                petType,
                rs.getInt("age"),
                rs.getBoolean("adopted"),
                shelter
        );
    }

    private RuntimeException mapSqlException(SQLException e, String name) {
        String sqlState = e.getSQLState();
        if ("23505".equals(sqlState)) { // unique_violation
            return new InvalidInputException("Pet with name '" + name + "' already exists.");
        }
        if ("23503".equals(sqlState)) { // foreign_key_violation
            return new InvalidInputException("Shelter id does not exist for this pet.");
        }
        return new RuntimeException("Database error: " + e.getMessage(), e);
    }
}
