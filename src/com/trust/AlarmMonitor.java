package com.trust;

import javax.print.attribute.standard.Severity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlarmMonitor {
    private final List<Equipment> equipment = new ArrayList<>();
    private final List<Alarm> activeAlarms = new ArrayList<>();
    private final Random random = new Random();
    private final AlarmLogger logger;

    public AlarmMonitor(AlarmLogger logger) {
        this.logger = logger;
    }

    public void addEquipment(Equipment e) {
        equipment.add(e);
    }

    // Simulate one scan cycle
    public void tick() {
        for (Equipment e : equipment) {
            double middle = (e.getMinLevel() + e.getMaxLevel()) / 2;
            double span = e.getMaxLevel() - e.getMinLevel();
            double newReading = middle + random.nextGaussian() * span * 0.35;
            e.setSensorReading(newReading);

            AlarmSeverity status = e.checkLevelStatus();
            if (status != AlarmSeverity.NORMAL) {
                Alarm alarm = new Alarm(e.getName(), newReading, status);
                activeAlarms.add(alarm);
                logger.log(alarm);
                System.out.println("NEW ALARM: " + alarm);
            }
        }
        System.out.println("Scan complete.");
    }

    public void showStatus() {
        System.out.println("\n--- Equipment Status ---");
        for (Equipment e : equipment) {
            System.out.printf("%-10s %8.1f %-3s  [%s]%n",
                    e.getName(), e.getSensorReading(), e.getUnit(), e.checkLevelStatus());
        }
    }

    public void listAlarms() {
        if (activeAlarms.isEmpty()) {
            System.out.println("No active alarms.");
            return;
        }
        System.out.println("\n--- Active Alarms ---");
        for (int i = 0; i < activeAlarms.size(); i++) {
            System.out.println((i + 1) + ". " + activeAlarms.get(i));
        }
    }

    public void acknowledge(int number) {
        if (number < 1 || number > activeAlarms.size()) {
            System.out.println("No alarm with that number.");
            return;
        }
        Alarm removed = activeAlarms.remove(number - 1);
        System.out.println("Acknowledged: " + removed);
    }
}
