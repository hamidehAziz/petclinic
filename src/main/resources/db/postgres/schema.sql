DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;


CREATE TABLE postgres_vets (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);

CREATE INDEX vets_last_name ON postgres_vets (last_name);

CREATE TABLE postgres_specialties (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON postgres_specialties (name);

CREATE TABLE postgres_vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE postgres_vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES postgres_vets (id);
ALTER TABLE postgres_vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES postgres_specialties (id);

CREATE TABLE postgres_types (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON postgres_types (name);

CREATE TABLE postgres_owners (
  id         SERIAL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON postgres_owners (last_name);

CREATE TABLE postgres_pets (
  id         INTEGER PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER NOT NULL
);
ALTER TABLE postgres_pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES postgres_owners (id);
ALTER TABLE postgres_pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES postgres_types (id);
CREATE INDEX pets_name ON postgres_pets (name);

CREATE TABLE postgres_visits (
  id          INTEGER PRIMARY KEY,
  pet_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE postgres_visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES postgres_pets (id);
CREATE INDEX visits_pet_id ON postgres_visits (pet_id);
