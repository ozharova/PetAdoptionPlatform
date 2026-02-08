CREATE TABLE IF NOT EXISTS shelters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(255),
    rating DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS pets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    age INTEGER,
    adopted BOOLEAN DEFAULT FALSE,
    shelter_id INTEGER,
    CONSTRAINT fk_shelter
        FOREIGN KEY(shelter_id)
        REFERENCES shelters(id)
        ON DELETE SET NULL
);

-- Optional: Insert initial data if tables are empty
INSERT INTO shelters (name, location, rating) VALUES
('Test1', '52 Turan Ave.', 4.6),
('Paw patrol', '14 Tole Bi St.', 3.9),
('Zootopia', '123 Uly Dala St.', 5.0)
ON CONFLICT DO NOTHING;
