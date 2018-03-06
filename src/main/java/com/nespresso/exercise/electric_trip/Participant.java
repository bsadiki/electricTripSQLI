package com.nespresso.exercise.electric_trip;

public class Participant {
    private City currentCity;
    private int batterySize;
    private int lowSpeedPerformance;
    private int highSpeedPerformance;
    private Double charge;

    public Participant(City currentCity, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        this.currentCity = currentCity;
        this.batterySize = batterySize;
        this.lowSpeedPerformance = lowSpeedPerformance;
        this.highSpeedPerformance = highSpeedPerformance;
        this.charge = Double.valueOf(batterySize);
    }

    public String getCharge() {
        int chargePercentage = (int) Math.rint((charge * 100) / batterySize);
        return (chargePercentage + "%");
    }

    public String position() {
        return currentCity.getName();
    }

    public void go() {
        travel(lowSpeedPerformance);
    }

    public void sprint() {
        travel(highSpeedPerformance);

    }

    private void travel(int speedPerformance) {
        City city = currentCity.getNextCity();
        while (city != null) {
            Integer distanceToNextCity = currentCity.getRoadToNextCity();
            Double requiredEnergy = (Double.valueOf(distanceToNextCity)) / speedPerformance;
            if (currentCity.getChargePerHour() != null) {
                Double requiredEnergyToNextChargingCity = requiredEnergyToNextChargingCity(speedPerformance);
                if( charge<batterySize && requiredEnergyToNextChargingCity > charge )
                    break;
            }  if (requiredEnergy < charge) {
                charge -= requiredEnergy;
                currentCity = city;
                city = currentCity.getNextCity();
            } else
                break;
        }
    }

    public void chargeEnergy(int hoursOfCharge) {
        Integer chargePerHour = currentCity.getChargePerHour();
        if (chargePerHour != null) {
            int energyToAdd = hoursOfCharge * chargePerHour;
            charge += energyToAdd;
            if (charge>batterySize)
                charge = Double.valueOf(batterySize);
        }
    }

    private Double requiredEnergyToNextChargingCity(int speedPerformance) {
        Integer distance = currentCity.getRoadToNextCity();
        City city = currentCity.getNextCity();
        while (city != null && city.getChargePerHour() == null) {
            if (city.getRoadToNextCity() != null) {
                distance += city.getRoadToNextCity();
                city = city.getNextCity();
            }
            else break;
        }
        return (Double.valueOf(distance)) / speedPerformance;
    }
}
