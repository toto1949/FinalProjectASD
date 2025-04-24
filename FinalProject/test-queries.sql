-- Use the database
USE adsdental;

-- 1. Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNames
SELECT d.id, d.name, d.specialization
FROM dentist d
ORDER BY d.name ASC;

-- 2. Display the list of ALL Appointments for a given Dentist by their dentist_Id number. Include in the result, the Patient information.
SELECT 
    a.id as appointment_id,
    a.appointment_date_time,
    d.name as dentist_name,
    d.specialization,
    p.first_name as patient_first_name,
    p.last_name as patient_last_name,
    p.email as patient_email,
    addr.street as patient_street,
    addr.city as patient_city,
    addr.zip as patient_zip
FROM appointment a
JOIN dentist d ON a.dentist_id = d.id
JOIN patient p ON a.patient_id = p.id
JOIN address addr ON p.address_id = addr.id
WHERE d.id = 1;

-- 3. Display the list of ALL Appointments that have been scheduled at a Surgery Location
SELECT 
    a.id as appointment_id,
    a.appointment_date_time,
    s.name as surgery_name,
    s.location as surgery_location,
    d.name as dentist_name,
    p.first_name as patient_first_name,
    p.last_name as patient_last_name
FROM appointment a
JOIN surgery s ON a.surgery_id = s.id
JOIN dentist d ON a.dentist_id = d.id
JOIN patient p ON a.patient_id = p.id
WHERE s.id = 1;

-- 4. Display the list of the Appointments booked for a given Patient on a given Date
SELECT 
    a.id as appointment_id,
    a.appointment_date_time,
    d.name as dentist_name,
    d.specialization,
    s.name as surgery_name,
    s.location as surgery_location
FROM appointment a
JOIN patient p ON a.patient_id = p.id
JOIN dentist d ON a.dentist_id = d.id
JOIN surgery s ON a.surgery_id = s.id
WHERE p.id = 1 
AND DATE(a.appointment_date_time) = '2024-03-20'; 