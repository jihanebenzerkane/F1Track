package org.Formula1;

import org.Formula1.dao.DriverDAO;
import org.Formula1.dao.RaceDAO;
import org.Formula1.dao.RaceResultDAO;
import org.Formula1.db.DataBaseManager;
import org.Formula1.models.Driver;


import java.util.List;
import java.util.Scanner;

import static org.Formula1.service.DriverService.manageDrivers;
import static org.Formula1.service.RaceService.manageRaces;
import static org.Formula1.service.ResultService.manageResults;

public class Main {

    public static void main(String[] args) {

        DataBaseManager.createTables();
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.findAll();

        for (Driver d : drivers) {
            System.out.println(d);
        }
        RaceDAO raceDAO = new RaceDAO();
        RaceResultDAO resultDAO = new RaceResultDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Formula 1 Management ===");
            System.out.println("1. View Drivers");
            System.out.println("2. View Races");
            System.out.println("3. Manage Race Results");
            System.out.println("4. Statistics");
            System.out.println("5. Predictions");
            System.out.println("0. Exit");
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
                    System.out.println("Statistics feature coming soon!");
                    break;
                case 5:
                    System.out.println("Predictions feature coming soon!");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
