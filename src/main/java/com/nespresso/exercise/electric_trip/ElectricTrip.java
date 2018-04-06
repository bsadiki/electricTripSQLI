package com.nespresso.exercise.electric_trip;

import com.nespresso.exercise.electric_trip.city.CitiesBuilder;
import com.nespresso.exercise.electric_trip.city.City;
import com.nespresso.exercise.electric_trip.participant.Participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ElectricTrip {
    private List<Participant> participants;
    private HashMap<String, City> cities;


    public ElectricTrip(String citiesAndDistances) {
        participants = new ArrayList<>();
        cities = new CitiesBuilder().buildCities(citiesAndDistances);
    }

    public int startTripIn(String start, int batterySize, int lowSpeedPerformance, int highSpeedPerformance) {
        City city = cities.get(start);
        Participant participant = new Participant(city, batterySize, lowSpeedPerformance, highSpeedPerformance);
        participants.add(participant);
        return participants.size() - 1;
    }

    public void go(int participantId) {
         participants.get(participantId).go();
    }

    public String locationOf(int participantId) {
        return participants.get(participantId).position();
    }

    public String chargeOf(int participantId) {
        return participants.get(participantId).currentChargePercentage();
    }

    public void sprint(int participantId) {
        participants.get(participantId).sprint();
    }

    public void charge(int participantId, int hoursOfCharge) {
         participants.get(participantId).charge(hoursOfCharge);
    }
}
