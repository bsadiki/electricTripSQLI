package com.nespresso.exercise.electric_trip;

import static com.nespresso.exercise.electric_trip.Config.CITY_SEPARATOR;

public class City {
    private String name;
    private Integer chargePerHour;
    private Integer roadToNextCity;
    private City nextCity;

    public City(String cityInfo, Integer roadToNextCity, City nextCity) {
        String[] cityInfos = cityInfo.split(CITY_SEPARATOR);
        name = cityInfos[0];
        if (cityInfos.length == 2) {
            chargePerHour = Integer.valueOf(cityInfos[1]);
        }
        this.roadToNextCity = roadToNextCity;
        this.nextCity = nextCity;
    }

    public String getName() {
        return name;
    }

    public Integer getRoadToNextCity() {
        return roadToNextCity;
    }

    public Integer getChargePerHour() {
        return chargePerHour;
    }

    public City getNextCity() {
        return nextCity;
    }
    public boolean hasAdjacent(){
        return nextCity !=null;
    }
}
