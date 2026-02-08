package com.petadoption.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ShelterRepository implements IShelterRepository {
    private final DataSource dataSource;

    public ShelterRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM shelters WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("[Database Error] " + e.getMessage());
            return false;
        }
    }
}
