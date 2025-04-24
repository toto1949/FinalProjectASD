-- Use the database
USE adsdental;

-- Clear existing data
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE appointment;
TRUNCATE TABLE patient;
TRUNCATE TABLE dentist;
TRUNCATE TABLE surgery;
TRUNCATE TABLE address;
SET FOREIGN_KEY_CHECKS = 1;

-- Insert test addresses
INSERT INTO address (street, city, zip) VALUES
('123 Main St', 'New York', '10001'),
('456 Park Ave', 'New York', '10002'),
('789 Broadway', 'New York', '10003');

-- Insert test patients
INSERT INTO patient (first_name, last_name, email, address_id) VALUES
('John', 'Doe', 'john@example.com', 1),
('Jane', 'Smith', 'jane@example.com', 2),
('Bob', 'Johnson', 'bob@example.com', 3);

-- Insert test dentists
INSERT INTO dentist (name, specialization) VALUES
('Dr. Smith', 'General Dentistry'),
('Dr. Jones', 'Orthodontics'),
('Dr. Brown', 'Pediatric Dentistry');

-- Insert test surgeries
INSERT INTO surgery (name, location) VALUES
('Main Clinic', '123 Main St, New York'),
('Downtown Branch', '456 Park Ave, New York'),
('Westside Clinic', '789 Broadway, New York');

-- Insert test appointments
INSERT INTO appointment (appointment_date_time, patient_id, dentist_id, surgery_id) VALUES
('2024-03-20 09:00:00', 1, 1, 1),
('2024-03-21 10:30:00', 2, 2, 2),
('2024-03-22 14:00:00', 3, 3, 3); 