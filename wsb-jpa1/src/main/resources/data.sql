-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (1, 'xx', 'yy', 'city', '62-030');

-- Dane dla Address
INSERT INTO address (id, city, address_line1, address_line2, postal_code) VALUES
                                                                             (1, 'Warsaw', 'Ulica Krótka 1', NULL, '00-001'),
                                                                             (2, 'Krakow', 'Ulica Długa 15', 'Apt. 5', '30-001');

-- Dane dla Doctor
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
                                                                                                                    (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@test.com', 'DOC123', 'KARDIOLOGIA', 1),
                                                                                                                    (2, 'Anna', 'Nowak', '987654321', 'anna.nowak@test.com', 'DOC456', 'NEUROLOGIA', 2);

-- Dane dla Patient
INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id) VALUES
                                                                                                                   (1, 'Piotr', 'Zielinski', '111222333', 'piotr.zielinski@test.com', 'PAT123', '1990-05-15', 1),
                                                                                                                   (2, 'Maria', 'Kowalska', '444555666', 'maria.kowalska@test.com', 'PAT456', '1985-08-20', 2);


-- Dane dla Visit
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES
                                                                                   (1, 'Kontrola', '2024-11-20 10:00:00', 1, 1),
                                                                                   (2, 'Leczenie specjalistyczne', '2024-11-21 15:00:00', 2, 2);

-- Dane dla MedicalTreatment
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES
                                                           (1, 'Chirurgia serca', 'Chirurgia', 1),
                                                           (2, 'Terapia', 'Terapia', 2);
