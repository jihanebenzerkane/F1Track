-- TEAMS
CREATE TABLE teams (
                       id INTEGER PRIMARY KEY,
                       name TEXT NOT NULL
);

INSERT INTO teams VALUES
                      (1, 'Red Bull'),
                      (2, 'Ferrari'),
                      (3, 'Mercedes'),
                      (4, 'McLaren'),
                      (5, 'Aston Martin');

--------------------------------------------------

-- DRIVERS
CREATE TABLE drivers (
                         id INTEGER PRIMARY KEY,
                         name TEXT NOT NULL,
                         nationality TEXT,
                         team_id INTEGER,
                         FOREIGN KEY (team_id) REFERENCES teams(id)
);

INSERT INTO drivers VALUES
                        (1, 'Max Verstappen', 'Netherlands', 1),
                        (2, 'Sergio Perez', 'Mexico', 1),
                        (3, 'Charles Leclerc', 'Monaco', 2),
                        (4, 'Carlos Sainz', 'Spain', 2),
                        (5, 'Lewis Hamilton', 'UK', 3),
                        (6, 'George Russell', 'UK', 3),
                        (7, 'Lando Norris', 'UK', 4),
                        (8, 'Oscar Piastri', 'Australia', 4),
                        (9, 'Fernando Alonso', 'Spain', 5),
                        (10, 'Lance Stroll', 'Canada', 5);

--------------------------------------------------

-- RACES
CREATE TABLE races (
                       id INTEGER PRIMARY KEY,
                       name TEXT,
                       circuit TEXT,
                       season INTEGER
);

INSERT INTO races VALUES
                      (1, 'Bahrain GP', 'Sakhir', 2024),
                      (2, 'Saudi Arabia GP', 'Jeddah', 2024),
                      (3, 'Australia GP', 'Melbourne', 2024),
                      (4, 'Japan GP', 'Suzuka', 2024),
                      (5, 'Monaco GP', 'Monaco', 2024);