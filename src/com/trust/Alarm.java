package com.trust;

import javax.print.attribute.standard.Severity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alarm {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String equipmentName;
    private final double value;
    private final AlarmSeverity severity;
    private final LocalDateTime time;

    public Alarm(String equipmentName, double value, AlarmSeverity severity) {
        this.equipmentName = equipmentName;
        this.value = value;
        this.severity = severity;
        this.time = LocalDateTime.now();
    }

    public AlarmSeverity getSeverity() {
        return this.severity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-8s %s = %.1f",
                time.format(FMT), this.severity, this.equipmentName, this.value);
    }

    public String toCsv() {
        return time.format(FMT) + "," + this.equipmentName + "," + this.severity + ","
                + String.format("%.1f", this.value);
    }
}
