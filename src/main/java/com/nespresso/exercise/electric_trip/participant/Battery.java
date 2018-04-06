package com.nespresso.exercise.electric_trip.participant;

import com.nespresso.exercise.electric_trip.city.City;

public class Battery {
    private final int size;
    private Double currentCharge;

    public Battery(int size) {
        this.size = size;
        this.currentCharge = (double) size;
    }

    public void charge(City city, int hoursOfCharge) {
        currentCharge += city.charge(hoursOfCharge);
        if (currentCharge > size)
            currentCharge = (double) size;
    }

    public String currentChargePercentage() {
        int chargePercentage = (int) Math.rint((currentCharge * 100) / size);
        return (chargePercentage + "%");
    }

    public boolean canAfford(Double energy) {
        return energy <= currentCharge;
    }

    public void consume(double consummation) {
        this.currentCharge -= consummation;
    }

    public boolean fullyCharged() {
        return currentCharge == size;
    }

}
