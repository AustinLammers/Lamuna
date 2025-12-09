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
    parent_log_id INT NULL,
    user_id INT NOT NULL,
    CONSTRAINT fk_food_user FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT fk_food_parent FOREIGN KEY (parent_log_id) REFERENCES FoodLogs (log_id) ON DELETE CASCADE
);

CREATE TABLE WorkoutLogs (
    log_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR (200),
    date DATE DEFAULT NOW(),
    workout_type VARCHAR(20) NOT NULL CHECK (workout_type IN ('TIMED','REPS')),
    time INT NULL,
    sets INT NULL,
    reps INT NULL,
    calories_burnt INT,
    user_id INT NOT NULL,
    CONSTRAINT fk_workout_user FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- Indexes for FK lookups
CREATE INDEX idx_foodlogs_user ON FoodLogs(user_id);
CREATE INDEX idx_workoutlogs_user ON WorkoutLogs(user_id);

-- CREATE PLACEHOLDER USERS
INSERT INTO Users (first_name, last_name)
VALUES ('John', 'Doe');

INSERT INTO Users (first_name, last_name)
VALUES ('Jane', 'Dawson');

-- CREATE PLACEHOLDER LOGS
INSERT INTO FoodLogs (name, description, user_id)
VALUES  ('Salad', 'Salad with lettuce, tomato, and ranch', 1);

INSERT INTO WorkoutLogs (name, description, workout_type, time, calories_burnt, user_id)
VALUES  ('Walking', 'Walked around my neighborhood', 'TIMED', 20, 250, 2);

