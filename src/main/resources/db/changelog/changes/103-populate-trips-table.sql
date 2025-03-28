INSERT INTO trips (id, bus_id, departure_location, arrival_location, departure_time, arrival_time, price)
VALUES
    (nextval('trip_id_seq'), 1, 'Bucuresti', 'Cluj-Napoca', '2025-02-10 08:00:00', '2025-02-10 14:00:00', 120.50),
    (nextval('trip_id_seq'), 1, 'Bucuresti', 'Brasov', '2025-02-11 10:00:00', '2025-02-11 12:30:00', 50.00),
    (nextval('trip_id_seq'), 2, 'Iasi', 'Constanta', '2025-02-12 09:00:00', '2025-02-12 17:00:00', 150.00),
    (nextval('trip_id_seq'), 2, 'Cluj-Napoca', 'Timisoara', '2025-02-13 14:00:00', '2025-02-13 18:00:00', 80.00),
    (nextval('trip_id_seq'), 3, 'Sibiu', 'Oradea', '2025-02-14 07:00:00', '2025-02-14 12:00:00', 90.00),
    (nextval('trip_id_seq'), 3, 'Bucuresti', 'Iasi', '2025-02-15 06:00:00', '2025-02-15 14:00:00', 130.00),
    (nextval('trip_id_seq'), 4, 'Craiova', 'Brasov', '2025-02-16 08:30:00', '2025-02-16 12:30:00', 70.00),
    (nextval('trip_id_seq'), 5, 'Galati', 'Bacau', '2025-02-17 09:00:00', '2025-02-17 11:30:00', 60.00),
    (nextval('trip_id_seq'), 5, 'Timisoara', 'Arad', '2025-02-18 10:00:00', '2025-02-18 11:00:00', 40.00),
    (nextval('trip_id_seq'), 1, 'Sibiu', 'Cluj-Napoca', '2025-02-19 12:00:00', '2025-02-19 15:00:00', 75.00);
