-- CREACIÓN DE MODULOS
INSERT INTO module (name, base_path) VALUES ('VETERINARIO', '/veterinarios');
INSERT INTO module (name, base_path) VALUES ('ESPECIALIDAD', '/especialidad');
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');

-- CREACIÓN DE MÓDULO PARA RETO SECCION 11
INSERT INTO module (name, base_path) VALUES ('PERMISSION', '/permissions');

-- CREACIÓN DE OPERACIONES
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_VETERINARIOS','', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_VETERINARIOS','/[0-9]*', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_VETERINARIOS','', 'POST', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_VETERINARIOS','/[0-9]*', 'PUT', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_VETERINARIOS','/[0-9]*/disabled', 'PUT', false, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_ESPECIALIDAD','', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_ESPECIALIDAD','/[0-9]*', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_ESPECIALIDAD','', 'POST', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_ESPECIALIDAD','/[0-9]*', 'PUT', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_ESPECIALIDAD','/[0-9]*/disabled', 'PUT', false, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, 3);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE','/validate', 'GET', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_PROFILE','/profile','GET', false, 4);

-- CREACIÓN DE OPERACIONES DE MÓDULO PARA RETO SECCION 11
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PERMISSIONS','','GET', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PERMISSION','/[0-9]*','GET', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PERMISSION','','POST', false, 5);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ONE_PERMISSION','/[0-9]*','DELETE', false, 5);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('OPERATOR');
INSERT INTO role (name) VALUES ('ADMINISTRATOR');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 15);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 15);

INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 5);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 10);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 15);

-- CREACIÓN DE PERMISOS PARA RETO SECCION 11
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 16);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 17);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 18);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 19);

-- CREACIÓN DE USUARIOS
INSERT INTO usuario (username, name, password, role_id) VALUES ('lmarquez', 'luis márquez', '$2a$10$sUITtLBJGY6GxYRDXodjvuOiR2IvY4C7t/tMdm4EGvZ0/GtH.sgGi', 1);
INSERT INTO usuario (username, name, password, role_id) VALUES ('fperez', 'fulano pérez', '$2a$10$uCeFoJPHERu5dCJZTEQIsOC8WqdhN8QsOT2nJIVeUjy7yIfqxAHUq', 2);
INSERT INTO usuario (username, name, password, role_id) VALUES ('mhernandez', 'mengano hernández', '$2a$10$kvD/uzv976mSDfgTo0LslOrUDLwZxNphWFEue5/XwM.1J8XtDS9wC', 3);

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
