package org.Formula1.service;

import org.Formula1.dao.DriverDAO;

import java.util.Scanner;

public class DriverService {
    public static void manageDrivers(DriverDAO driverDAO, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Manage Drivers ===");
            System.out.println("1. List Drivers");
            System.out.println("2. Search Driver by name");
            System.out.println("3. Search Driver by nationality");
            System.out.println("4. Search Driver by team");
            System.out.println("5. View Driver Standings");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewDrivers(driverDAO);
                    break;
                case 2:
                    searchDriverByName(driverDAO, scanner);
                    break;
                case 3:
                    searchDriverByNationality(driverDAO, scanner);
                    break;
                case 4:
                    searchDriverByTeam(driverDAO, scanner);
                    break;
                case 5:
                    viewDriverStandings(driverDAO);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void searchDriverByName(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        driverDAO.findByName(name).forEach(d -> System.out.println("ID: " + d.getId() + " | " +"Name: "+ d.getName() + " | " +"Nationality: "+ d.getNationality() + " | " +"Car Number: "+ d.getCarNumber  () + " | " +"Team: "+ d.getTeam() + " | " +"Points: "+ d.getPoints() + " | "));
    }

    private static void searchDriverByNationality(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();
        driverDAO.findByNationality(nationality).forEach(d -> System.out.println("ID: " + d.getId() + " | " +"Name: "+ d.getName() + " | " +"Nationality: "+ d.getNationality() + " | " +"Car Number: "+ d.getCarNumber  () + " | " +"Team: "+ d.getTeam() + " | " +"Points: "+ d.getPoints() + " | "));
    }

    private static void searchDriverByTeam(DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter team: ");
        String team = scanner.nextLine();
        driverDAO.findByTeam(team).forEach(d -> System.out.println("ID: " + d.getId() + " | " +"Name: "+ d.getName() + " | " +"Nationality: "+ d.getNationality() + " | " +"Car Number: "+ d.getCarNumber  () + " | " +"Team: "+ d.getTeam() + " | " +"Points: " +d.getPoints() + " | "));
    }

    private static void viewDriverStandings(DriverDAO driverDAO) {
        driverDAO.findAll().stream().sorted((d1, d2) -> d2.getPoints() - d1.getPoints()).forEach(d -> System.out.println("ID: " + d.getId() + " | " +"Name: "+d.getName() + " | " +"Nationality: "+ d.getNationality() + " | " +"Car Number: "+ d.getCarNumber  () + " | " +"Team: "+ d.getTeam() + " | " +"Points: " +d.getPoints() + " | "));
    }

    private static void viewDrivers(DriverDAO driverDAO) {
        driverDAO.findAll().forEach(d -> System.out.println("ID: " + d.getId() + " | " +"Name: " + d.getName() + " | " +"Nationality:   "+ d.getNationality() + " | " +"Car Number: "+ d.getCarNumber  () + " | " +"Team: "+ d.getTeam() + " | " +"Points: "+ d.getPoints() + " | "));
    }
    /*private static void updateDriver(DriverDAO driverDAO, Scanner scanner) {
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
    }*/
}
