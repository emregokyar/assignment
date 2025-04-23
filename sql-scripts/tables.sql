GRANT CONNECT ON DATABASE assignment TO devuser;

CREATE TABLE authors (
	id SERIAL PRIMARY KEY,
	full_name VARCHAR(255)
);

CREATE TABLE packages (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	author_id INT NOT NULL,
	CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE versions (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	package_id INT NOT NULL,
	CONSTRAINT fk_package FOREIGN KEY (package_id) REFERENCES packages(id)
);