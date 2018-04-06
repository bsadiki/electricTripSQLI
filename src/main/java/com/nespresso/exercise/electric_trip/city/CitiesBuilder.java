package com.nespresso.exercise.electric_trip.city;

import java.util.HashMap;


public class CitiesBuilder {
    private static final String DISTANCE_SEPARATOR ="-";
    public HashMap<String, City> buildCities(String citiesAndDistances) {
        String[] citiesAndDistancesInfo = citiesAndDistances.split(DISTANCE_SEPARATOR);
        HashMap<String, City> cities = new HashMap<>();
        City destinationCity = null;
        for (int i = citiesAndDistancesInfo.length - 1; i >= 0; i = i - 2) {
            String cityInfo = citiesAndDistancesInfo[i];
            Integer nextRoadDistance = getNextRoadDistance(citiesAndDistancesInfo, i);
            destinationCity = buildCity(destinationCity, cityInfo, nextRoadDistance);
            cities.put(destinationCity.name, destinationCity);
        }
        return cities;
    }

    private Integer getNextRoadDistance(String[] citiesAndDistancesInfo, int i) {
        Integer nextRoadDistance = null;
        if (i < citiesAndDistancesInfo.length - 1)
            nextRoadDistance = Integer.valueOf(citiesAndDistancesInfo[i + 1]);
        return nextRoadDistance;
    }

    private City buildCity(City destinationCity, String cityInfo, Integer nextRoadDistance) {
        return new CityFactory().create(cityInfo, nextRoadDistance, destinationCity);
    }
}
