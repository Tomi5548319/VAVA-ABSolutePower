CREATE TABLE IF NOT EXISTS account_types(
	id serial PRIMARY KEY,
	type varchar(20) UNIQUE NOT NULL
);

INSERT INTO account_types (type)
VALUES ('admin');

INSERT INTO account_types (type)
VALUES ('verifier');

INSERT INTO account_types (type)
VALUES ('sportsman');

INSERT INTO account_types (type)
VALUES ('trainer');

CREATE TABLE IF NOT EXISTS users(
	id serial PRIMARY KEY,
	account_type INT NOT NULL,
	login varchar(50) UNIQUE NOT NULL,
	password varchar(50) NOT NULL,
	
	FOREIGN KEY (account_type)
      REFERENCES account_types (id)
);

INSERT INTO users (account_type, login, password)
VALUES (1, 'admin', 'ehqmr');

INSERT INTO users (account_type, login, password)
VALUES (2, 'verifier', 'zivmjmiv');

INSERT INTO users (account_type, login, password)
VALUES (3, 'sportsman', 'wtsvxwqer');

INSERT INTO users (account_type, login, password)
VALUES (4, 'trainer', 'xvemriv');

CREATE TABLE IF NOT EXISTS user_activity_logs(
	id serial PRIMARY KEY,
	activity TEXT NOT NULL,
	time TIMESTAMP NOT NULL,
	user_id INT NOT NULL,
	
	FOREIGN KEY (user_id)
      REFERENCES users (id)
);