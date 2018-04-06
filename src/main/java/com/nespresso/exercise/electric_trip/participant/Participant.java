package com.nespresso.exercise.electric_trip.participant;

import com.nespresso.exercise.electric_trip.city.City;

public class Participant {
    private final int lowSpeedPerformance;
    private final int highSpeedPerformance;
    private final Battery battery;
    private City currentCity;

    public Participant(City currentCity, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this.currentCity = currentCity;
        this.battery = new Battery(batterySize);
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
    }

    public String currentChargePercentage() {
        return battery.currentChargePercentage();
    }

    public String position() {
        return currentCity.name;
    }

    public void go() {
        travel(lowSpeedPerformance);
    }

    public void sprint() {
        travel(highSpeedPerformance);

    }

    private void travel(int speedPerformance) {
        while (canTravelToNextCity(speedPerformance)) {
            travelToNextCity(requiredEnergyToNextCity(speedPerformance));
        }
    }

    private boolean canTravelToNextCity(int speedPerformance) {
        return currentCity.hasNextCity() && canReachNextCity(requiredEnergyToNextCity(speedPerformance)) && !MustCharge(speedPerformance);
    }

    private boolean canReachNextCity(Double requiredEnergyToNextCity) {
        return battery.canAfford(requiredEnergyToNextCity);
    }

    private Double requiredEnergyToNextCity(int speedPerformance) {
        Integer distanceToNextCity = currentCity.distanceToNextCity();
        return ((double) distanceToNextCity) / speedPerformance;
    }

    private boolean MustCharge(int speedPerformance) {
        return (notFullyCharged() && canCharge() && !canReachNextChargingCity(speedPerformance));
    }

    private boolean notFullyCharged() {
        return !battery.fullyCharged();
    }

    private boolean canCharge() {
        return currentCity.hasCharger();
    }

    private boolean canReachNextChargingCity(int speedPerformance) {
        return battery.canAfford(requiredEnergyToNextChargingCity(speedPerformance));
    }

    private void travelToNextCity(Double requiredChargeToNextCity) {
        battery.consume(requiredChargeToNextCity);
        currentCity = currentCity.getNextCity();
    }

    public void charge(int hoursOfCharge) {
       battery.charge(currentCity,hoursOfCharge);
    }

    private Double requiredEnergyToNextChargingCity(int speedPerformance) {
        Integer distance = currentCity.distanceToNextChargingCity();
        return ((double) distance) / speedPerformance;
    }
}
