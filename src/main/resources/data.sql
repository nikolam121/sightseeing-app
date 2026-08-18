INSERT INTO SIGHTSEEING.LOCATION (id, name) VALUES (1, 'Zagreb');
INSERT INTO SIGHTSEEING.LOCATION (id, name) VALUES (2, 'Split');
INSERT INTO SIGHTSEEING.LOCATION (id, name) VALUES (3, 'Zadar');

INSERT INTO SIGHTSEEING.ATTRACTION_METADATA (id, location_id) VALUES (1, 1);
INSERT INTO SIGHTSEEING.ATTRACTION_METADATA (id, location_id) VALUES (2, 2);
INSERT INTO SIGHTSEEING.ATTRACTION_METADATA (id, location_id) VALUES (3, 3);

INSERT INTO SIGHTSEEING.ATTRACTION (id, name, description, type, attraction_id)
VALUES (1, 'Mimara Museum', 'Art museum in Zagreb', 'MUSEUM', 1);

INSERT INTO SIGHTSEEING.ATTRACTION (id, name, description, type, attraction_id)
VALUES (2, 'Ban Jelacic Square', 'Zagreb''s main square', 'STREET', 1);

INSERT INTO SIGHTSEEING.ATTRACTION (id, name, description, type, attraction_id)
VALUES (3, 'Maksimir Park', 'Large park in eastern Zagreb, a place to relax', 'PARK', 1);

INSERT INTO SIGHTSEEING.ATTRACTION (id, name, description, type, attraction_id)
VALUES (4, 'Diocletian''s Palace', 'Remains of a Roman palace in the center of Split', 'MUSEUM', 2);

INSERT INTO SIGHTSEEING.ATTRACTION (id, name, description, type, attraction_id)
VALUES (5, 'Sea Organ', 'Sea organ on the Zadar waterfront', 'STREET', 3);

ALTER TABLE SIGHTSEEING.LOCATION ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE SIGHTSEEING.ATTRACTION_METADATA ALTER COLUMN ID RESTART WITH 100;
ALTER TABLE SIGHTSEEING.ATTRACTION ALTER COLUMN ID RESTART WITH 100;
