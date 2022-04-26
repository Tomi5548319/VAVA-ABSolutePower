CREATE TABLE IF NOT EXISTS account_types(
	id serial PRIMARY KEY,
	type varchar(20) UNIQUE NOT NULL
);

INSERT INTO account_types (type) VALUES
('admin'),
('sportsman'),
('trainer');

CREATE TABLE IF NOT EXISTS languages(
	id serial PRIMARY KEY,
	language varchar(20) NOT NULL,
	lang varchar(2) NOT NULL,
	country varchar(30) NOT NULL,
	cntry varchar(2) NOT NULL
);

INSERT INTO languages (language, lang, country, cntry) VALUES
('English', 'en', 'United Kingdom', 'GB'),
('Slovak', 'sk', 'Slovakia', 'SK');

CREATE TABLE IF NOT EXISTS users(
	id serial PRIMARY KEY,
	account_type INT NOT NULL,
	language_id INT NOT NULL,
	login varchar(50) UNIQUE NOT NULL,
	password varchar(50) NOT NULL,
	
	FOREIGN KEY (account_type)
      REFERENCES account_types (id)
	  ON DELETE CASCADE,
	  
	FOREIGN KEY (language_id)
      REFERENCES languages (id)
	  ON DELETE CASCADE
);

INSERT INTO users (account_type, language_id, login, password) VALUES
(1, 1, 'admin', 'ehqmr'),
(2, 1, 'sportsman', 'wtsvxwqer'),
(3, 1, 'trainer', 'xvemriv'),
(1, 2, 'admin-sk', 'ehqmr'),
(2, 2, 'sportsman-sk', 'wtsvxwqer'),
(3, 2, 'trainer-sk', 'xvemriv');

CREATE TABLE IF NOT EXISTS user_activity_logs(
	id serial PRIMARY KEY,
	activity TEXT NOT NULL,
	time TIMESTAMP NOT NULL,
	user_id INT NOT NULL -- user_id nenastavime na foreign key aby nam log ostal aj po vymazani usera
);

CREATE TABLE IF NOT EXISTS sportsmen(
	id serial PRIMARY KEY,
	nickname varchar(50),
	avatar_id INT NOT NULL,
	description TEXT,
	weight DECIMAL,
	height DECIMAL,
	banned BOOLEAN NOT NULL,
	banned_untill TIMESTAMP,
	user_id INT UNIQUE NOT NULL,
	
	FOREIGN KEY (user_id)
      REFERENCES users (id)
	  ON DELETE CASCADE
);

INSERT INTO sportsmen (nickname, avatar_id, description, weight, height, banned, user_id) VALUES
('English sportsman', 1, 'Best English sportsman in the whole wide world', 50, 153, false, 2),
('English trainer', 1, 'Best English trainer in the whole wide world', 62, 170, false, 3),
('Slovensky sportovec', 1, 'Najlepsi Slovensky sportovec na celom svete', 81, 190, false, 5),
('Slovensky trener', 1, 'Najlepsi Slovensky trener na celom svete', 70, 172, false, 6);

CREATE TABLE IF NOT EXISTS sportsman_trainers(
	id serial PRIMARY KEY,
	active BOOLEAN NOT NULL,
	trainer_id INT NOT NULL,
	sportsman_id INT NOT NULL,
	rating INT,
	
	FOREIGN KEY (trainer_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE
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
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (trainer_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS training_questions_answers(
	id serial PRIMARY KEY,
	answer TEXT NOT NULL,
	request_id INT NOT NULL,
	question_id INT NOT NULL,
	
	FOREIGN KEY (request_id)
      REFERENCES training_requests (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS report_reasons(
	id serial PRIMARY KEY,
	name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS workouts(
	id serial PRIMARY KEY,
	name varchar(50) NOT NULL,
	owner_id INT NOT NULL,
	description TEXT,
	scheduled_for TIMESTAMP,
	
	FOREIGN KEY (owner_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE
);

INSERT INTO workouts (name, owner_id, description) VALUES
('Basic workout', 2, 'Workout for beginners');

CREATE TABLE IF NOT EXISTS exercises(
	id serial PRIMARY KEY,
	name varchar(50) NOT NULL,
	description TEXT
);

INSERT INTO exercises (name) VALUES
('stretch'),
('plank'),
('push-up'),
('diamond push-up'),
('planche'),
('dip'),
('bench-press'),
('muscle-up'),
('pull-up'),
('row'),
('suspended row'),
('hyper extension'),
('front lever'),
('back lever'),
('shoulder-press'),
('handstand'),
('handstand push-up'),
('run'),
('sprint'),
('rope skipping'),
('leg-press'),
('lunge'),
('squat'),
('calf raise'),
('sit-up'),
('leg-raise'),
('L-sit'),
('V-sit');

CREATE TABLE IF NOT EXISTS exercises_in_workouts(
	id serial PRIMARY KEY,
	sets INT NOT NULL,
	exercise_id INT NOT NULL,
	workout_id INT NOT NULL,
	completed_sets INT NOT NULL,
	repetitions INT,
	extra_weight DECIMAL,
	rest INTERVAL NOT NULL,
	time_for_rep INTERVAL
	
	FOREIGN KEY (exercise_id)
      REFERENCES exercises (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (workout_id)
      REFERENCES workouts (id)
	  ON DELETE CASCADE
);

INSERT INTO exercises_in_workouts (sets, exercise_id, workout_id, completed_sets, repetitions, rest, time_for_rep) VALUES
(1, 1, 1, 0, null, '00:00:00', '00:05:00'),
(4, 3, 1, 0, 10, '00:03:30', null),
(4, 9, 1, 0, 8, '00:04:00', null);

CREATE TABLE IF NOT EXISTS competitions(
	id serial PRIMARY KEY,
	start_time TIMESTAMP NOT NULL,
	workout_id INT NOT NULL,
	end_time TIMESTAMP NOT NULL,
	max_participants INT,
	
	FOREIGN KEY (workout_id)
      REFERENCES workouts (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS participants(
	id serial PRIMARY KEY,
	sportsman_id INT NOT NULL,
	progress INT NOT NULL,
	competition_id INT NOT NULL,
	paused BOOLEAN NOT NULL,
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (competition_id)
      REFERENCES competitions (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS personal_bests(
	id serial PRIMARY KEY,
	extra_weight INT,
	sportsman_id INT NOT NULL,
	exercise_id INT NOT NULL,
	repetitions INT,
	time INTERVAL,
	reps_selected BOOLEAN NOT NULL,
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (exercise_id)
      REFERENCES exercises (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reports(
	id serial PRIMARY KEY,
	date TIMESTAMP NOT NULL,
	reporter_id INT NOT NULL,
	reported_id INT NOT NULL,
	reason INT NOT NULL,
	workout_id INT,
	description TEXT,
	solved BOOLEAN NOT NULL,
	
	FOREIGN KEY (reporter_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (reported_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (reason)
      REFERENCES report_reasons (id)
	  ON DELETE CASCADE,
	
	FOREIGN KEY (workout_id)
      REFERENCES workouts (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS upgrade_requests(
	id serial PRIMARY KEY,
	created_at TIMESTAMP NOT NULL,
	sportsman_id INT NOT NULL,
	accepted boolean,
	
	FOREIGN KEY (sportsman_id)
      REFERENCES sportsmen (id)
	  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS upgrade_questions_answers(
	id serial PRIMARY KEY,
	answer TEXT NOT NULL,
	request_id INT NOT NULL,
	question_id INT NOT NULL,
	
	FOREIGN KEY (request_id)
      REFERENCES upgrade_requests (id)
	  ON DELETE CASCADE
);