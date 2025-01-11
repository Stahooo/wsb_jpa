-- insert into address (id, address_line1, address_line2, city, postal_code)
--             values (1, 'xx', 'yy', 'city', '62-030');

-- Dane dla Address
INSERT INTO address (id, city, address_line1, address_line2, postal_code, version) VALUES
                                                                             (1, 'Warsaw', 'Ulica Krótka 1', NULL, '00-001', 0),
                                                                             (2, 'Krakow', 'Ulica Długa 15', 'Apt. 5', '30-001', 0),
                                                                             (3, 'Krakow', 'Ulica Długa 16', 'Apt. 7', '30-001', 0);

-- Dane dla Doctor
INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id) VALUES
                                                                                                                    (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@test.com', 'DOC123', 'SURGEON', 1),
                                                                                                                    (2, 'Anna', 'Nowak', '987654321', 'anna.nowak@test.com', 'DOC456', 'DERMATOLOGIST', 2);

-- Dane dla Patient
INSERT INTO patient (id, first_name, last_name, telephone_number, email, is_woman, patient_number, date_of_birth, address_id) VALUES
                                                                                                                   (1, 'Piotr', 'Zielinski', '111222333', 'piotr.zielinski@test.com', false,'PAT123', '1990-05-15', 1),
                                                                                                                   (2, 'Maria', 'Kowalska', '444555666', 'maria.kowalska@test.com', true,'PAT456', '1985-08-20', 2,),
                                                                                                                   (3, 'Marian', 'Kowalski', '445555676', 'marian.kowalski@test.com', false,'PAT458', '1995-08-21', 3,),
                                                                                                                   (4, 'Marian2', 'Kowalski', '445555677', 'marian.kowalski2@test.com', false,'PAT500', '1999-08-21', 3),
                                                                                                                   (5, 'Marian3', 'Kowalski', '445555678', 'marian.kowalski3@test.com', false,'PAT501', '1999-09-21', 3),
                                                                                                                   (6, 'Marian4', 'Kowalski', '445555679', 'marian.kowalski4@test.com', false,'PAT502', '2001-08-21', 3),
                                                                                                                   (7, 'Marian5', 'Kowalski', '445555680', 'marian.kowalski5@test.com', false,'PAT503', '2002-08-21', 3);


-- Dane dla Visit
INSERT INTO visit (id, description, time, doctor_id, patient_id) VALUES
                                                                                   (1, 'Kontrola', '2024-11-20 10:00:00', 1, 1),
                                                                                   (2, 'Leczenie specjalistyczne', '2024-11-21 15:00:00', 2, 2),
                                                                                   (3, 'Leczenie specjalistyczne', '2024-11-23 15:00:00', 2, 2),
                                                                                   (4, 'Leczenie specjalistyczne', '2024-11-25 15:00:00', 2, 2),
                                                                                   (5, 'Leczenie specjalistyczne', '2024-11-26 15:00:00', 2, 2),
                                                                                   (6, 'Leczenie specjalistyczne', '2024-11-27 15:00:00', 2, 2),
                                                                                   (7, 'Leczenie specjalistyczne', '2024-11-28 15:00:00', 2, 2);

-- Dane dla MedicalTreatment
INSERT INTO medical_treatment (id, description, type, visit_id) VALUES
                                                           (1, 'Chirurgia serca', 'EKG', 1),
                                                           (2, 'Terapia', 'USG', 2);
