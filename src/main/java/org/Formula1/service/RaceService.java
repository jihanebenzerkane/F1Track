package org.Formula1.service;

import org.Formula1.dao.RaceDAO;
import org.Formula1.models.Race;

import java.util.Date;
import java.util.Scanner;

public class RaceService {
    public static void manageRaces(RaceDAO raceDAO, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Manage Races ===");
            System.out.println("1. Add Race");
            System.out.println("2. List Races");
            System.out.println("3. Update Race");
            System.out.println("4. Delete Race");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addRace(raceDAO, scanner);
                    break;
                case 2:
                    listRaces(raceDAO);
                    break;
                case 3:
                    updateRace(raceDAO, scanner);
                    break;
                case 4:
                    deleteRace(raceDAO, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private static void addRace(RaceDAO raceDAO, Scanner scanner) {
        System.out.print("Enter Grand Prix: ");
        String gp = scanner.nextLine();
        System.out.print("Enter Season: ");
        int season = scanner.nextInt();
        scanner.nextLine();
        raceDAO.insert(new Race(0, new Date(), gp, season, "", ""));
    }

    private static void listRaces(RaceDAO raceDAO) {
        raceDAO.findAll().forEach(r -> System.out.println("ID: " + r.getId() + " | " + r.getGrandPrix() + " (" + r.getSeason() + ")"));
    }

    private static void updateRace(RaceDAO raceDAO, Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Race r = raceDAO.findById(id);
        if (r != null) {
            System.out.print("New Grand Prix (" + r.getGrandPrix() + "): ");
            String gp = scanner.nextLine();
            if (!gp.isEmpty()) r.setGrandPrix(gp);
            raceDAO.update(r);
        }
    }

    private static void deleteRace(RaceDAO raceDAO, Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        raceDAO.delete(id);
    }
}

