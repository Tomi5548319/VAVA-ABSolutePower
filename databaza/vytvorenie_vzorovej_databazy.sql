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

CREATE TABLE IF NOT EXISTS profile_avatars(
	id serial PRIMARY KEY,
	photo_url TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS sportsmen(
	id serial PRIMARY KEY,
	nickname varchar(50) NOT NULL,
	avatar_id INT NOT NULL,
	description TEXT,
	weight DECIMAL,
	height DECIMAL,
	banned BOOLEAN NOT NULL,
	banned_untill TIMESTAMP,
	user_id INT NOT NULL,
	
	FOREIGN KEY (avatar_id)
      REFERENCES profile_avatars (id),
	
	FOREIGN KEY (user_id)
      REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS sportsman_trainers(
	id serial PRIMARY KEY,
	active BOOLEAN NOT NULL,
	trainer_id INT NOT NULL,
	sportsman_id INT NOT NULL,
	rating INT,
	
	FOREIGN KEY (trainer_id)
      REFERENCES sportsmen (id),
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id)
);

CREATE TABLE IF NOT EXISTS training_requests(
	id serial PRIMARY KEY,
	created_at TIMESTAMP NOT NULL,
	sportsman_id INT NOT NULL,
	trainer_id INT NOT NULL,
	expires_at TIMESTAMP NOT NULL,
	accepted BOOLEAN,
	reply_text TEXT,
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id),
	
	FOREIGN KEY (trainer_id)
      REFERENCES sportsmen (id)
);

CREATE TABLE IF NOT EXISTS languages(
	id serial PRIMARY KEY,
	language varchar(20) NOT NULL
);