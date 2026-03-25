package org.Formula1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:f1track.db"; //our database address
    public static Connection connect() throws SQLException { //openning the database connection
        Connection conn = DriverManager.getConnection(URL);
        try(Statement stmt = conn.createStatement()){
            stmt.execute("PRAGMA foreign_keys=ON");
        } catch (SQLException ignored){
        }return conn;
    }
    public static void createTables(){
        String driver = """
                CREATE TABLE IF NOT EXISTS driver (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date TEXT,
                    name TEXT,
                    team TEXT,
                    points INTEGER,
                    numberOfPrizes INTEGER,
                    nationality TEXT,
                    carNumber INTEGER
                    );
                """;
        String race = """
                CREATE TABLE IF NOT EXISTS race (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    grandPrix TEXT,
                    raceDate DATE,
                    season INTEGER,
                    country TEXT,
                    circuit TEXT
                    );
                """;
        String raceResult ="""
                CREATE TABLE IF NOT EXISTS raceResult(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    raceId INTEGER,
                    driverId INTEGER,
                    finishPosition INTEGER,
                    gridPosition INTEGER,
                    pointsEarned INTEGER,
                    fastestLap BOOLEAN,
                    dnf BOOLEAN,
                    dnfReason TEXT
                    );
                """;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys=ON");
            stmt.execute(driver);
            stmt.execute(raceResult);
            stmt.execute(race);

            String insertDrivers = """
                    INSERT INTO driver (date, name, team, points, numberOfPrizes, nationality, carNumber) VALUES
                    ('2026-03-25', 'Max Verstappen', 'Red Bull Racing', 25, 50, 'Netherlands', 1),
                    ('2026-03-25', 'Charles Leclerc', 'Ferrari', 25, 50, 'Monaco', 16),
                    ('2026-03-25', 'Lando Norris', 'McLaren', 25, 50, 'UK', 4),
                    ('2026-03-25', 'Carlos Sainz', 'Ferrari', 25, 50, 'Spain', 55),
                    ('2026-03-25', 'Sergio Perez', 'Red Bull Racing', 25, 50, 'Mexico', 11),
                    ('2026-03-25', 'George Russell', 'Mercedes', 25, 50, 'UK', 63),
                    ('2026-03-25', 'Lewis Hamilton', 'Mercedes', 25, 50, 'UK', 44),
                    ('2026-03-25', 'Fernando Alonso', 'Aston Martin', 25, 50, 'Spain', 14),
                    ('2026-03-25', 'Pierre Gasly', 'Alpine', 25, 50, 'France', 10),
                    ('2026-03-25', 'Esteban Ocon', 'Alpine', 25, 50, 'France', 31),
                    ('2026-03-25', 'Valtteri Bottas', 'Sauber', 25, 50, 'Finland', 77),
                    ('2026-03-25', 'Zhou Guanyu', 'Sauber', 25, 50, 'China', 24),
                    ('2026-03-25', 'Alex Albon', 'Williams', 25, 50, 'Thailand', 23),
                    ('2026-03-25', 'Logan Sargeant', 'Williams', 25, 50, 'USA', 2),
                    ('2026-03-25', 'Daniel Ricciardo', 'RB', 25, 50, 'Australia', 3),
                    ('2026-03-25', 'Yuki Tsunoda', 'RB', 25, 50, 'Japan', 22),
                    ('2026-03-25', 'Lance Stroll', 'Aston Martin', 25, 50, 'Canada', 18),
                    ('2026-03-25', 'Kevin Magnussen', 'Haas', 25, 50, 'Denmark', 20),
                    ('2026-03-25', 'Nico Hulkenberg', 'Haas', 25, 50, 'Germany', 27),
                    ('2026-03-25', 'Oliver Bearman', 'Ferrari', 25, 50, 'UK', 38),
                    ('2026-03-25', 'Andrea Kimi Antonelli', 'Mercedes', 25, 50, 'Italy', 63);
                    """;
            
            String insertRaceResults = """
                    INSERT INTO raceResult (raceId, driverId, finishPosition, gridPosition, pointsEarned, fastestLap, dnf, dnfReason) VALUES
                    (1, 1, 1, 1, 25, 1, 0, null),
                    (1, 2, 2, 2, 18, 0, 0, null),
                    (1, 3, 3, 3, 15, 0, 0, null),
                    (1, 4, 4, 4, 12, 0, 0, null),
                    (1, 5, 5, 5, 10, 0, 0, null),
                    (1, 6, 6, 6, 8, 0, 0, null),
                    (1, 7, 7, 7, 6, 0, 0, null),
                    (1, 8, 8, 8, 4, 0, 0, null),
                    (1, 9, 9, 9, 2, 0, 0, null),
                    (1, 10, 10, 10, 1, 0, 0, null),
                    (1, 11, 11, 11, 0, 0, 0, null),
                    (1, 12, 12, 12, 0, 0, 0, null),
                    (1, 13, 13, 13, 0, 0, 0, null),
                    (1, 14, 14, 14, 0, 0, 0, null),
                    (1, 15, 15, 15, 0, 0, 0, null),
                    (1, 16, 16, 16, 0, 0, 0, null),
                    (1, 17, 17, 17, 0, 0, 0, null),
                    (1, 18, 18, 18, 0, 0, 0, null),
                    (1, 19, 19, 19, 0, 0, 0, null),
                    (1, 20, 20, 20, 0, 0, 0, null),
                    (1, 21, 21, 21, 0, 0, 0, null),
                    (1, 22, 22, 22, 0, 0, 0, null);
                    """;

            String insertRaces = """
                    INSERT INTO race (grandPrix, raceDate, season, country, circuit) VALUES
                    ('Bahrain Grand Prix', '2026-03-25', 2026, 'Bahrain', 'Bahrain International Circuit'),
                    ('Saudi Arabian Grand Prix', '2026-03-25', 2026, 'Saudi Arabia', 'Jeddah Street Circuit'),
                    ('Australian Grand Prix', '2026-03-25', 2026, 'Australia', 'Albert Park Circuit'),
                    ('Chinese Grand Prix', '2026-03-25', 2026, 'China', 'Shanghai International Circuit'),
                    ('Miami Grand Prix', '2026-03-25', 2026, 'USA', 'Miami International Autodrome'),
                    ('Emilia Romagna Grand Prix', '2026-03-25', 2026, 'Italy', 'Imola Circuit'),
                    ('Monaco Grand Prix', '2026-03-25', 2026, 'Monaco', 'Circuit de Monaco'),
                    ('Canadian Grand Prix', '2026-03-25', 2026, 'Canada', 'Circuit Gilles Villeneuve'),
                    ('Spanish Grand Prix', '2026-03-25', 2026, 'Spain', 'Circuit de Barcelona-Catalunya'),
                    ('Austrian Grand Prix', '2026-03-25', 2026, 'Austria', 'Red Bull Ring'),
                    ('British Grand Prix', '2026-03-25', 2026, 'UK', 'Silverstone Circuit'),
                    ('Hungarian Grand Prix', '2026-03-25', 2026, 'Hungary', 'Hungaroring'),
                    ('Belgian Grand Prix', '2026-03-25', 2026, 'Belgium', 'Circuit de Spa-Francorchamps'),
                    ('Dutch Grand Prix', '2026-03-25', 2026, 'Netherlands', 'Zandvoort Circuit'),
                    ('Italian Grand Prix', '2026-03-25', 2026, 'Italy', 'Monza Circuit'),
                    ('Azerbaijan Grand Prix', '2026-03-25', 2026, 'Azerbaijan', 'Baku City Circuit'),
                    ('Singapore Grand Prix', '2026-03-25', 2026, 'Singapore', 'Marina Bay Street Circuit'),
                    ('US Grand Prix', '2026-03-25', 2026, 'USA', 'Circuit of the Americas'),
                    ('Mexico City Grand Prix', '2026-03-25', 2026, 'Mexico', 'Autódromo Hermanos Rodríguez'),
                    ('São Paulo Grand Prix', '2026-03-25', 2026, 'Brazil', 'Interlagos Circuit'),
                    ('Las Vegas Grand Prix', '2026-03-25', 2026, 'USA', 'Las Vegas Strip Circuit'),
                    ('Qatar Grand Prix', '2026-03-25',2026, 'Qatar', 'Lusail International Circuit');
                    """;
            try {
                stmt.execute(insertDrivers);
                stmt.execute(insertRaces);
                stmt.execute(insertRaceResults);
            } catch (SQLException e) {
                // Ignore if unique constraints fail on subsequent runs
                System.out.println("Note: Some data might already exist in the database.");
            }

            System.out.println("Base de donnees initialisee !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
