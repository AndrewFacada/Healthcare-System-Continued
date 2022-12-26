--CREATE DATABASE ProjectTwo;

DROP TABLE IF EXISTS covid_info;
DROP TABLE IF EXISTS claim;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS payments;

CREATE TABLE user_info(
	user_id SERIAL PRIMARY KEY NOT NULL CHECK(user_id > 0),
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	DoB DATE NOT NULL,
	SSN VARCHAR(11) NOT NULL,
	address VARCHAR(100) NOT NULL,
	current_employee BOOLEAN NOT NULL,
	current_subscriber BOOLEAN NOT NULL
);

CREATE TABLE covid_info(
	covid_id SERIAL PRIMARY KEY NOT NULL CHECK(covid_id > 0),
	user_id INT NOT NULL,
--	vaccinated BOOLEAN NOT NULL,
	vaccine_type VARCHAR(100),
	vaccination_date DATE DEFAULT(null),
	FOREIGN KEY (user_id) REFERENCES user_info(user_id)
);

CREATE TABLE payments( 
	payment_id SERIAL PRIMARY KEY NOT NULL CHECK(payment_id > 0),
	user_id INT NOT NULL,
	card_type VARCHAR(40) NOT NULL,
	cvv INT NOT NULL,
	exp_date DATE,
	amount INT NOT NULL,
	
	due_date DATE,
	description VARCHAR(100) NOT NULL
);

create table claim(
	claim_id SERIAL primary key CHECK(claim_id > 0),
	user_id INT not null, 
	claim_description Varchar(50),
	amount float check (amount>0),
	status varchar(20) default 'pending',
	submission_date varchar(20) NOT NULL,
	decision_date varchar(20) default null,
	foreign key (user_id) references user_info(user_id)
);

INSERT INTO user_info(first_name, last_name, email, password, DoB, SSN, address, current_employee, current_subscriber)
	VALUES('alex', 'abrams', 'email@email.com', 'password', '1991-11-04', '736-33-4334', '222 drive road', true, false),
		('alex1', 'abrams1', 'email1@email.com', 'password1', '1991-11-04', '736-33-4334', '222 drive road', false, true),
		('alex2', 'abrams2', 'email2@email.com', 'password2', '1991-11-04', '736-33-4334', '222 drive road', false, true),
		('alex3', 'abrams3', 'email3@email.com', 'password4', '1991-11-04', '736-33-4334', '222 drive road', true, true);


INSERT INTO covid_info(user_id, vaccine_type, vaccination_date)
	VALUES(1, 'Moderna', '2020-11-11'),
	(2, 'JJ', '2020-1-11'),
	(3, 'JJ', '2021-04-17'),
	(2, 'Ivermectin', '2021-04-18'),
	(4, 'Bleach', '2021-04-20');

SELECT user_id, first_name, last_name, vaccine_type, vaccination_date 
FROM covid_info 
JOIN user_info USING (user_id)
WHERE user_id = 2;



INSERT INTO claim(user_id, claim_description, amount, submission_date) VALUES 
(2,'test description', 104, '2022-12-16'),
(1,'test description', 99, '2022-12-16'),
(2,'test description', 100, '2022-12-16'),
(1,'test description', 101, '2022-12-16'),
(2,'test description', 102, '2022-12-16'),
(3,'test description', 40, '2022-12-16');
SELECT * FROM claim WHERE user_id=2;
UPDATE claim SET status='accepted' WHERE claim_id=2;
SELECT * from claim where NOT user_id=2;
