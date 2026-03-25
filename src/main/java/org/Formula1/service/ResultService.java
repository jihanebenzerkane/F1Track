package org.Formula1.service;

import org.Formula1.dao.DriverDAO;
import org.Formula1.dao.RaceDAO;
import org.Formula1.dao.RaceResultDAO;
import org.Formula1.models.Driver;
import org.Formula1.models.Race;
import org.Formula1.models.RaceResult;

import java.util.List;
import java.util.Scanner;

public class ResultService {
    public static void manageResults(RaceResultDAO resultDAO, DriverDAO driverDAO, RaceDAO raceDAO, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Manage Results ===");
            System.out.println("1. Record Race Result");
            System.out.println("2. View Results by Race");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    recordResult(resultDAO, driverDAO, raceDAO, scanner);
                    break;
                case 2:
                    viewResults(resultDAO, raceDAO, driverDAO, scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void recordResult(RaceResultDAO resultDAO, DriverDAO driverDAO, RaceDAO raceDAO, Scanner scanner) {
        System.out.println("\n--- Select Race ---");
        List<Race> races = raceDAO.findAll();
        races.forEach(r -> System.out.println(r.getId() + ". " + r.getGrandPrix()));
        System.out.print("Race ID: ");
        int raceId = scanner.nextInt();

        System.out.println("\n--- Select Driver ---");
        List<Driver> drivers = driverDAO.findAll();
        drivers.forEach(d -> System.out.println(d.getId() + ". " + d.getName()));
        System.out.print("Driver ID: ");
        int driverId = scanner.nextInt();

        System.out.print("Finish Position: ");
        int pos = scanner.nextInt();
        System.out.print("Grid Position: ");
        int grid = scanner.nextInt();
        
        StandingService standingService = new StandingService();
        int points = standingService.posToPoints(pos);

        RaceResult result = new RaceResult(driverId, raceId, pos, 0, grid, points);
        resultDAO.insert(result);
        System.out.println("Result recorded! Points earned: " + points);
    }

    private static void viewResults(RaceResultDAO resultDAO, RaceDAO raceDAO, DriverDAO driverDAO, Scanner scanner) {
        System.out.print("Enter Race ID to view: ");
        int raceId = scanner.nextInt();
        List<RaceResult> results = resultDAO.findByRaceId(raceId);
        
        System.out.println("\nResults for Race ID " + raceId + ":");
        results.forEach(res -> {
            Driver d = driverDAO.findById(res.getDriverId());
            String name = (d != null) ? d.getName() : "Unknown";
            System.out.println("Pos: " + res.getFinishPosition() + " | Driver: " + name + " | Points: " + res.getPointsEarned());
        });
    }
}
