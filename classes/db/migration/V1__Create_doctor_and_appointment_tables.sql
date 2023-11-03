CREATE TABLE doctor (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE appointment (
     id SERIAL PRIMARY KEY,
     doctor_id INT REFERENCES doctor(id),
     patient_first_name VARCHAR(255) NOT NULL,
     patient_last_name VARCHAR(255) NOT NULL,
     appointment_date_time TIMESTAMP NOT NULL,
     kind VARCHAR(50) NOT NULL CHECK (kind IN ('New Patient', 'Follow-up'))
);

CREATE INDEX idx_appointment_doctor_datetime ON appointment(doctor_id, appointment_date_time);
