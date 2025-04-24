-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS adsdental;

-- Use the database
USE adsdental;

-- -- Create roles table if it doesn't exist
-- CREATE TABLE IF NOT EXISTS roles (
--     role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(50) NOT NULL UNIQUE
-- );

-- -- Create users table
-- CREATE TABLE IF NOT EXISTS users (
--     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(100) NOT NULL UNIQUE,
--     first_name VARCHAR(50) NOT NULL,
--     middle_name VARCHAR(50),
--     last_name VARCHAR(50) NOT NULL,
--     account_non_expired BOOLEAN DEFAULT TRUE,
--     account_non_locked BOOLEAN DEFAULT TRUE,
--     credentials_non_expired BOOLEAN DEFAULT TRUE,
--     enabled BOOLEAN DEFAULT TRUE
-- );

-- -- Create user_roles table
-- CREATE TABLE IF NOT EXISTS user_roles (
--     user_id BIGINT,
--     role_id BIGINT,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users(user_id),
--     FOREIGN KEY (role_id) REFERENCES roles(role_id)
-- );

-- -- Create surgery_locations table
-- CREATE TABLE IF NOT EXISTS surgery_locations (
--     location_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(100) NOT NULL,
--     address VARCHAR(255) NOT NULL,
--     phone VARCHAR(20)
-- );

-- -- Create patients table
-- CREATE TABLE IF NOT EXISTS patients (
--     patient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     user_id BIGINT,
--     date_of_birth DATE,
--     phone_number VARCHAR(20),
--     FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );

-- -- Create dentists table
-- CREATE TABLE IF NOT EXISTS dentists (
--     dentist_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     user_id BIGINT,
--     specialization VARCHAR(100),
--     FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );

-- -- Create appointments table
-- CREATE TABLE IF NOT EXISTS appointments (
--     appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     patient_id BIGINT,
--     dentist_id BIGINT,
--     location_id BIGINT,
--     appointment_date DATE,
--     appointment_time TIME,
--     status VARCHAR(20),
--     FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
--     FOREIGN KEY (dentist_id) REFERENCES dentists(dentist_id),
--     FOREIGN KEY (location_id) REFERENCES surgery_locations(location_id)
-- );

-- Clear existing roles to ensure clean state
DELETE FROM roles;

-- Insert roles with specific IDs
INSERT INTO roles (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES (2, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (3, 'ROLE_DENTIST');
INSERT INTO roles (role_id, name) VALUES (4, 'ROLE_PATIENT');

-- Reset auto-increment to ensure consistent IDs
ALTER TABLE roles AUTO_INCREMENT = 5; 