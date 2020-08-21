create schema warehouse;
CREATE TABLE warehouse.countries (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT COUNTRIES_PKEY PRIMARY KEY (id));
CREATE TABLE warehouse.contractor_types (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT CONTRACTOR_TYPES_PKEY PRIMARY KEY (id));
CREATE TABLE warehouse.contractors (id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, name VARCHAR(255) NOT NULL, phone_number VARCHAR(255) NOT NULL, country_id INTEGER NOT NULL, contractor_type_id INTEGER NOT NULL, CONSTRAINT CONTRACTORS_PKEY PRIMARY KEY (id), CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES warehouse.countries(id), CONSTRAINT contractor_type_id FOREIGN KEY (contractor_type_id) REFERENCES warehouse.contractor_types(id));
