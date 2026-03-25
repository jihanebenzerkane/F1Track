package org.Formula1.service;

import org.Formula1.dao.RaceDAO;
import java.util.Scanner;

public class RaceService {
    public static void manageRaces(RaceDAO raceDAO, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Manage Races ===");
            System.out.println("1. List Races");
            System.out.println("2. Search Race by name");
            System.out.println("3. Search Race by season");
            System.out.println("4. View Race Standings");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    listRaces(raceDAO);
                    break;
                case 2:
                    searchRaceByName(raceDAO, scanner);
                    break;
                case 3:
                    searchRaceBySeason(raceDAO, scanner);
                    break;
                case 4:
                    viewRaceStandings(raceDAO);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private static void searchRaceByName(RaceDAO raceDAO, Scanner scanner) {
        System.out.print("Enter Grand Prix: ");
        String gp = scanner.nextLine();
        raceDAO.findByName(gp).forEach(r -> System.out.println("ID: " + r.getId() + " | " +"Grand Prix: "+ r.getGrandPrix() + " | " +"Season: "+ r.getSeason() +"Circuit: "+ r.getCircuit() + " | "));
    }

    private static void searchRaceBySeason(RaceDAO raceDAO, Scanner scanner) {
        System.out.print("Enter Season: ");
        int season = scanner.nextInt();
        scanner.nextLine();
        raceDAO.findBySeason(season).forEach(r -> System.out.println("ID: " + r.getId() + " | " +"Grand Prix: "+ r.getGrandPrix() + " | " +"Season: "+ r.getSeason() +"Circuit: "+ r.getCircuit() + " | "));
    }

    private static void listRaces(RaceDAO raceDAO) {
        raceDAO.findAll().forEach(r -> System.out.println("ID: " + r.getId() + " | " +"Grand Prix: "+ r.getGrandPrix() + " | " +"Season: "+ r.getSeason() +"Circuit: "+ r.getCircuit() + " | "));
    }
    private static void viewRaceStandings(RaceDAO raceDAO) {
        raceDAO.findAll().stream().sorted((r1, r2) -> r2.getSeason() - r1.getSeason()).forEach(r -> System.out.println("ID: " + r.getId() + " | " +"Grand Prix: "+ r.getGrandPrix() + " | " +"Season: "+ r.getSeason() +"Circuit: "+ r.getCircuit() + " | "));
    }
/*
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
*/
}

