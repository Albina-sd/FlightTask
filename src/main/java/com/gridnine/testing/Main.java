package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // получение всего набора перелетов
        FlightBuilder fb = new FlightBuilder();
        List<Flight> allFlights = fb.createFlights();

        System.out.println("All flights:\n");
        allFlights.stream()
                .map(o -> o.getSegments())
                .forEach((p) -> System.out.println(p));

        // текущее время
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        System.out.println("\nCurrent date: " + currentTime.format(fmt));

        FilterFlightService filterFlightsService = new FilterFlightsServiceImpl(allFlights, currentTime);

        filterFlightsService.arriveSegmentsEarlierDeparture();
        filterFlightsService.departureDateBeforeCurrTime();
        filterFlightsService.earthTimeMoreThenTwoHours();

        filterFlightsService.applyAllFilters();
    }
}
