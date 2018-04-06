package com.nespresso.exercise.electric_trip.city;

public class City {
    private final Integer chargePerHour;
    private final Integer roadToNextCity;
    private final City nextCity;
    public final String name;

    City(String name, Integer chargePerHour, Integer roadToNextCity, City nextCity) {
        this.name = name;
        this.chargePerHour = chargePerHour;
        this.roadToNextCity = roadToNextCity;
        this.nextCity = nextCity;
    }

    public boolean hasCharger() {
        return chargePerHour != null;
    }

    public Integer charge(int hoursOfCharge) {
        if (hasCharger())
            return hoursOfCharge * chargePerHour;
        else return 0;
    }

    public Integer distanceToNextCity() {
        return roadToNextCity;
    }

    public Integer distanceToNextChargingCity() {
        Integer distance = roadToNextCity;
        City city = nextCity;
        while (city != null && !city.hasCharger()) {
            if (city.roadToNextCity != null) {
                distance += city.roadToNextCity;
                city = city.nextCity;
            } else break;
        }
        return distance;
    }

    public boolean hasNextCity() {
        return nextCity != null;
    }

    public City getNextCity() {
        return nextCity;
    }
}
