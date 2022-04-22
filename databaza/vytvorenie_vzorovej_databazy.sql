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

CREATE TABLE IF NOT EXISTS languages(
	id serial PRIMARY KEY,
	language varchar(20) NOT NULL
);

INSERT INTO languages (language)
VALUES ('english');

INSERT INTO languages (language)
VALUES ('slovak');

CREATE TABLE IF NOT EXISTS users(
	id serial PRIMARY KEY,
	account_type INT NOT NULL,
	language_id INT NOT NULL,
	login varchar(50) UNIQUE NOT NULL,
	password varchar(50) NOT NULL,
	
	FOREIGN KEY (account_type)
      REFERENCES account_types (id),
	  
	FOREIGN KEY (language_id)
      REFERENCES languages (id)
);

INSERT INTO users (account_type, language_id, login, password)
VALUES (1, 1, 'admin', 'ehqmr');

INSERT INTO users (account_type, language_id, login, password)
VALUES (2, 1, 'verifier', 'zivmjmiv');

INSERT INTO users (account_type, language_id, login, password)
VALUES (3, 1, 'sportsman', 'wtsvxwqer');

INSERT INTO users (account_type, language_id, login, password)
VALUES (4, 1, 'trainer', 'xvemriv');

INSERT INTO users (account_type, language_id, login, password)
VALUES (1, 2, 'admin-sk', 'ehqmr');

INSERT INTO users (account_type, language_id, login, password)
VALUES (2, 2, 'verifier-sk', 'zivmjmiv');

INSERT INTO users (account_type, language_id, login, password)
VALUES (3, 2, 'sportsman-sk', 'wtsvxwqer');

INSERT INTO users (account_type, language_id, login, password)
VALUES (4, 2, 'trainer-sk', 'xvemriv');

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

CREATE TABLE IF NOT EXISTS training_questions_answers(
	id serial PRIMARY KEY,
	answer TEXT NOT NULL,
	request_id INT NOT NULL,
	question_id INT NOT NULL,
	
	FOREIGN KEY (request_id)
      REFERENCES training_requests (id)
);