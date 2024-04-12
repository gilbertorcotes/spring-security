-- CREACIÓN DE CATEGORIAS
INSERT INTO especialidad (name) VALUES ('Equino');
INSERT INTO especialidad (name) VALUES ('Bovino');
INSERT INTO especialidad (name) VALUES ('Porcino');
INSERT INTO especialidad (name) VALUES ('Aviar');

-- CREACIÓN DE PRODUCTOS
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Juan Pérez', 'ENABLED', 1);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Luisa Zanchez', 'DISABLED', 1);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Reoberto Mendez', 'ENABLED', 1);

INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Pedro Anzures', 'ENABLED', 2);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Juan Lomeli', 'ENABLED', 2);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Eugenia Rosas', 'ENABLED', 2);

INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Jesus López', 'ENABLED', 3);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Rauel Montes', 'DISABLED', 3);

INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Ernesto Gonzalez', 'ENABLED', 4);
INSERT INTO veterinarios (name, status, especialidad_id) VALUES ('Leonel Nieto', 'ENABLED', 4);
