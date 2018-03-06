package com.nespresso.exercise.electric_trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.nespresso.exercise.electric_trip.Config.DISTANCE_SEPARATOR;

public class ElectricTrip {
    private List<Participant> participants;
    private HashMap<String, City> cities;


    public ElectricTrip(String citiesAndDistances) {
        participants = new ArrayList<>();
        cities = new HashMap<>();
        City destinationCity = null;
        String[] citiesAndDistancesInfos = citiesAndDistances.split(DISTANCE_SEPARATOR);
        for (int i = citiesAndDistancesInfos.length - 1; i >= 0; i = i - 2) {
            String cityInfo = citiesAndDistancesInfos[i];
            Integer nextRoadDistance = null;
            if (i < citiesAndDistancesInfos.length - 1)
                nextRoadDistance = Integer.valueOf(citiesAndDistancesInfos[i + 1]);
            City city = new City(cityInfo, nextRoadDistance, destinationCity);
            cities.put(city.getName(), city);
            destinationCity = city;
        }
    }

    public int startTripIn(String start, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        City city = cities.get(start);
        Participant participant = new Participant(city, batterySize, lowSpeedPerformance, highSpeedPerformance);
        participants.add(participant);
        return participants.size() - 1;
    }

    public void go(int participantId) {
        Participant participant = participants.get(participantId);
        participant.go();
    }

    public String locationOf(int participantId) {
        Participant participant = participants.get(participantId);
        return participant.position();
    }

    public String chargeOf(int participantId) {
        Participant participant = participants.get(participantId);
        return participant.getCharge();
    }

    public void sprint(int participantId) {
        Participant participant = participants.get(participantId);
        participant.sprint();
    }

    public void charge(int participantId, int hoursOfCharge) {
        Participant participant = participants.get(participantId);
        participant.chargeEnergy(hoursOfCharge);
    }
}
