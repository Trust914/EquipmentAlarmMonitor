package com.trust;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlarmLogger logger = new AlarmLogger("alarms.csv");
        AlarmMonitor monitor = new AlarmMonitor(logger);

        monitor.addEquipment(new Equipment("CRAC-01", "C", 18, 27));
        monitor.addEquipment(new Equipment("UPS-A", "%", 10, 80));
        monitor.addEquipment(new Equipment("PDU-3", "A", 0, 32));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Equipment Alarm Monitor ===");
            System.out.println("1. Show status");
            System.out.println("2. Run scan");
            System.out.println("3. List active alarms");
            System.out.println("4. Acknowledge alarm");
            System.out.println("5. Show alarm history");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> monitor.showStatus();
                case "2" -> monitor.tick();
                case "3" -> monitor.listAlarms();
                case "4" -> {
                    monitor.listAlarms();
                    System.out.print("Alarm number to acknowledge: ");
                    try {
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        monitor.acknowledge(n);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number.");
                    }
                }
                case "5" -> logger.printHistory();
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Monitor stopped.");
    }
}
