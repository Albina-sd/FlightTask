package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Фильтр вылет до текущего момента времени
 * В конструктор передаются все перелеты и текущее время на момент запуска фильтрации перелетов
 * @author Albina Safina
 */

public class DepartureDateBeforeCurrTime implements Filter {
    private List<Flight> flights;
    private LocalDateTime currentTime;
    private List<Flight> filteredFlights;

    public DepartureDateBeforeCurrTime(List<Flight> flights, LocalDateTime currentTime) {
        this.flights = flights;
        this.currentTime = currentTime;
    }

    private void FilteredFlights(){
        System.out.println("Rule: Departure before current date");

        Predicate<Segment> DepartureAfterCurrTime = f -> f.getDepartureDate().isAfter(currentTime);
        Predicate<Flight> flightFilter = o -> o.getSegments().stream().allMatch(DepartureAfterCurrTime);

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
