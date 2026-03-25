package org.Formula1;

import org.Formula1.dao.DriverDAO;
import org.Formula1.dao.RaceDAO;
import org.Formula1.dao.RaceResultDAO;
import org.Formula1.db.DataBaseManager;
import java.util.Scanner;

import static org.Formula1.service.DriverService.manageDrivers;
import static org.Formula1.service.RaceService.manageRaces;
import static org.Formula1.service.ResultService.manageResults;

public class Main {

    public static void main(String[] args) {
        
        DataBaseManager.createTables();
        DriverDAO driverDAO = new DriverDAO();
        RaceDAO raceDAO = new RaceDAO();
        RaceResultDAO resultDAO = new RaceResultDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Formula 1 Management ===");
            System.out.println("1. Manage Drivers");
            System.out.println("2. Manage Races");
            System.out.println("3. Manage Results");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    manageDrivers(driverDAO, scanner);
                    break;
                case 2:
                    manageRaces(raceDAO, scanner);
                    break;
                case 3:
                    manageResults(resultDAO, driverDAO, raceDAO, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
