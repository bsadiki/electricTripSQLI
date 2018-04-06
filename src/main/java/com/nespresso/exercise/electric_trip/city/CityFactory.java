package com.nespresso.exercise.electric_trip.city;

class CityFactory {
    private static final String CITY_SEPARATOR = ":";

    City create(String cityInfo, Integer roadToNextCity, City nextCity) {
        String[] cityInfos = cityInfo.split(CITY_SEPARATOR);
        String name = cityInfos[0];
        Integer chargePerHour = getChargePerHour(cityInfos);
        return new City(name, chargePerHour, roadToNextCity, nextCity);
    }

    private Integer getChargePerHour(String[] cityInfos) {
        Integer chargePerHour = null;
        if (cityInfos.length == 2) {
            chargePerHour = Integer.valueOf(cityInfos[1]);
        }
        return chargePerHour;
    }
}
