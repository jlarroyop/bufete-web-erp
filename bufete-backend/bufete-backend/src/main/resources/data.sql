INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (1, NOW(), NOW(), "Configuracion", "/", "ACTIVE", null);
INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (2, NOW(), NOW(), "Usuarios", "/users", "ACTIVE", 1);
INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (3, NOW(), NOW(), "Roles", "/roles", "ACTIVE", 1);
INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (4, NOW(), NOW(), "Configuracion", "/configuration", "ACTIVE", 1);
INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (5, NOW(), NOW(), "Compañias", "/companies", "ACTIVE", 1);
INSERT INTO app_options
  (id, created_at, updated_at, name, path, status, parent_id)
VALUES
  (6, NOW(), NOW(), "Monedas", "/currencies", "ACTIVE", 1);

INSERT INTO roles
  (id, created_at, updated_at, name, status, parent_id)
VALUES
  (1, NOW(), NOW(), "Admin", "ACTIVE", null);
INSERT INTO roles
  (id, created_at, updated_at, name, status, parent_id)
VALUES
  (2, NOW(), NOW(), "Contabilidad", "ACTIVE", null);

INSERT INTO role_app_options
  (app_option_id, role_id, created_at, updated_at, status)
VALUES
  (1, 1, NOW(), NOW(), "ACTIVE");

INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (1, NOW(), NOW(), "Industria La Popular", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (2, NOW(), NOW(), "Telus International", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (3, NOW(), NOW(), "Xoom Guatemala", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (4, NOW(), NOW(), "Grupo Golan", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (5, NOW(), NOW(), "Estrategia Digital", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (6, NOW(), NOW(), "Grupo Tae", "ACTIVE");
INSERT INTO companies
  (id, created_at, updated_at, name, status)
VALUES
  (7, NOW(), NOW(), "IGSS", "ACTIVE");

INSERT INTO users
  (id, created_at, updated_at, email, name, password, status, username)
VALUES
  (1, NOW(), NOW(), "jlarroyop@gmail.com", "Jorge Arroyo", "$2a$10$.2KH9RO41v01eF.FJV1q3utRtieRbBXJSFxVYchPrNIm54c0nJYBe", "ACTIVE", "jlarroyop");

INSERT INTO user_role_company
  (company_id, role_id, user_id, created_at, updated_at, status)
VALUES
  (1, 1, 1, NOW(), NOW(), "ACTIVE");