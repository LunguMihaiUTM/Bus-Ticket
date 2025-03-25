INSERT INTO buses (id, bus_number, capacity, type, company_name)
VALUES
    (nextval('bus_id_seq'), 'B-100-XYZ', 50, 'Coach', 'TransCar'),
    (nextval('bus_id_seq'), 'B-200-ABC', 45, 'MiniBus', 'ExpressLine'),
    (nextval('bus_id_seq'), 'B-300-DEF', 55, 'Coach', 'RapidTrans'),
    (nextval('bus_id_seq'), 'B-400-GHI', 40, 'MiniBus', 'CityBus'),
    (nextval('bus_id_seq'), 'B-500-JKL', 60, 'DoubleDecker', 'MegaBus');
