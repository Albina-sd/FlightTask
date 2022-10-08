package com.gridnine.testing;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Фильтр имеются сегменты с датой прилёта раньше даты вылета
 * В конструктор передаются все перелеты и текущее время на момент запуска фильтрации перелетов
 * @author Albina Safina
 */

public class ArriveSegmentsEarlierDeparture implements Filter {
    private List<Flight> flights;
    private List<Flight> filteredFlights;

    public ArriveSegmentsEarlierDeparture(List<Flight> flights) {
        this.flights = flights;
    }

    private void FilteredFlights(){
        Predicate<Segment> ArriveAfterDeparture = f -> f.getArrivalDate().isAfter(f.getDepartureDate());
        Predicate<Flight> flightFilter = o -> o.getSegments().stream().allMatch(ArriveAfterDeparture);

        System.out.println("Rule: Without segments with an arrival date earlier than the departure date");

        filteredFlights = flights.stream()
                .filter(Objects::nonNull)
                .filter(flightFilter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFilteredFlights() {
        FilteredFlights();
        return filteredFlights;
    }
}
