-- CREACIÓN DE USUARIOS
INSERT INTO usuario (username, name, password, role) VALUES ('lmarquez', 'luis márquez', '$2a$10$sUITtLBJGY6GxYRDXodjvuOiR2IvY4C7t/tMdm4EGvZ0/GtH.sgGi', 'CUSTOMER');
INSERT INTO usuario (username, name, password, role) VALUES ('fperez', 'fulano pérez', '$2a$10$uCeFoJPHERu5dCJZTEQIsOC8WqdhN8QsOT2nJIVeUjy7yIfqxAHUq', 'OPERATOR');
INSERT INTO usuario (username, name, password, role) VALUES ('mhernandez', 'mengano hernández', '$2a$10$kvD/uzv976mSDfgTo0LslOrUDLwZxNphWFEue5/XwM.1J8XtDS9wC', 'ADMINISTRATOR');

-- CREACIÓN DE CATEGORIAS
INSERT INTO especialidad (nombre) VALUES ('Equino');
INSERT INTO especialidad (nombre) VALUES ('Bovino');
INSERT INTO especialidad (nombre) VALUES ('Porcino');
INSERT INTO especialidad (nombre) VALUES ('Aviar');

-- CREACIÓN DE PRODUCTOS
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Juan Pérez', 'ENABLED', 1);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Luisa Zanchez', 'DISABLED', 1);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Reoberto Mendez', 'ENABLED', 1);

INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Pedro Anzures', 'ENABLED', 2);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Juan Lomeli', 'ENABLED', 2);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Eugenia Rosas', 'ENABLED', 2);

INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Jesus López', 'ENABLED', 3);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Rauel Montes', 'DISABLED', 3);

INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Ernesto Gonzalez', 'ENABLED', 4);
INSERT INTO veterinarios (nombre, status, especialidad_id) VALUES ('Leonel Nieto', 'ENABLED', 4);
