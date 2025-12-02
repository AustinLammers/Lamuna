-- CREATE TABLES
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    join_date DATE DEFAULT NOW()
);

CREATE TABLE FoodLogs (
    log_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR (200),
    date DATE DEFAULT NOW(),
    calories INT,
    protein FLOAT,
    carbs FLOAT,
    fat FLOAT,
    user_id INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE ExcersizeLogs (
    log_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR (200),
    date DATE DEFAULT NOW(),
    time INT NULL,
    sets INT NULL,
    reps INT NULL,
    calories_burnt INT,
    user_id INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- CREATE PLACEHOLDER USERS
INSERT INTO Users (first_name, last_name)
VALUES ('John', 'Doe');

INSERT INTO Users (first_name, last_name)
VALUES ('Jane', 'Dawson');

-- CREATE PLACEHOLDER LOGS
INSERT INTO FoodLogs (name, description, user_id)
VALUES  ('Salad', 'Salad with lettuce, tomato, and ranch', 1);

INSERT INTO ExcersizeLogs (name, description, time, calories_burnt, user_id)
VALUES  ('Walking', 'Walked around my neighborhood', 20, 250, 2);


