package com.trust;

import javax.print.attribute.standard.Severity;

public class Equipment {
    private  String name;
    private String type;
    private String unit;
    private double minLevel;
    private double maxLevel;
    private double sensorReading; // simulated

    public Equipment(String name, String unit, double minLevel, double maxLevel) {
        this.name = name;
        this.unit = unit;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.sensorReading = (this.minLevel + this.maxLevel) / 2; // start in the middle of the safe range
    }

    public AlarmSeverity checkLevelStatus() {
        double allowedMargin = (this.maxLevel - this.minLevel) * 0.2; // 20% below or above the limit is critical
        if (this.sensorReading < (this.minLevel - allowedMargin) || this.sensorReading > (this.maxLevel + allowedMargin)){
            return AlarmSeverity.CRITICAL;
        }
        if ( this.sensorReading < this.minLevel || this.sensorReading>this.maxLevel){
            return AlarmSeverity.WARNING;
        }
        return AlarmSeverity.NORMAL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(double minLevel) {
        this.minLevel = minLevel;
    }

    public double getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(double maxLevel) {
        this.maxLevel = maxLevel;
    }

    public double getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(double sensorReading) {
        this.sensorReading = sensorReading;
    }
}
