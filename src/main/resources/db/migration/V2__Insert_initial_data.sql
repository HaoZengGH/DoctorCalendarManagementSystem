-- Doctor Table Data
INSERT INTO doctor(id, first_name, last_name) VALUES (1, 'John', 'Doe');
INSERT INTO doctor(id, first_name, last_name) VALUES (2, 'Jane', 'Smith');
INSERT INTO doctor(id, first_name, last_name) VALUES (3, 'Lucy', 'Hart');

-- Appointment Table Data (assuming some sample data for simplicity)
INSERT INTO appointment(id, doctor_id, patient_first_name, patient_last_name, appointment_date_time, kind) VALUES (1, 1, 'Alan', 'Brown', '2023-10-22 08:15:00', 'New Patient');
INSERT INTO appointment(id, doctor_id, patient_first_name, patient_last_name, appointment_date_time, kind) VALUES (2, 1, 'Sara', 'Wilson', '2023-10-22 09:00:00', 'Follow-up');
INSERT INTO appointment(id, doctor_id, patient_first_name, patient_last_name, appointment_date_time, kind) VALUES (3, 2, 'George', 'Turner', '2023-10-22 10:30:00', 'New Patient');
