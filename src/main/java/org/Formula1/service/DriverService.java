package org.Formula1.service;

import org.Formula1.dao.DriverDAO;
import org.Formula1.models.Driver;

import java.util.Scanner;

public class DriverService {
    public static void manageDrivers(DriverDAO driverDAO, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Manage Drivers ===");
            System.out.println("1. Add Driver");
            System.out.println("2. List Drivers");
            System.out.println("3. Update Driver");
            System.out.println("4. Delete Driver");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addDriver(driverDAO, scanner);
                    break;
                case 2:
                    listDrivers(driverDAO);
                    break;
                case 3:
                    updateDriver(driverDAO, scanner);
                    break;
                case 4:
                    deleteDriver(driverDAO, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addDriver(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter team: ");
        String team = scanner.nextLine();
        System.out.print("Enter car number: ");
        int carNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();
        driverDAO.insert(new Driver(name, team, 0, carNumber, nationality));
    }

    private static void listDrivers(DriverDAO driverDAO) {
        driverDAO.findAll().forEach(d -> System.out.println("ID: " + d.getId() + " | " + d.getName() + " | " + d.getNationality() + " | " + d.getCarNumber  () + " | " + d.getTeam() + " | " + d.getPoints() + " | "));
    }

    private static void updateDriver(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Driver d = driverDAO.findById(id);
        if (d != null) {
            System.out.print("New name (" + d.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) d.setName(name);
            driverDAO.update(d);
        }
    }

    private static void deleteDriver(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        driverDAO.delete(id);
    }
}
