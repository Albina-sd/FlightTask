package com.gridnine.testing;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Фильтр общее время, проведённое на земле превышает два часа
 * (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
 * В конструктор передаются все перелеты
 * @author Albina Safina
 */

public class EarthTimeMoreThenTwoHours implements Filter {
    private List<Flight> flights;
    private List<Flight> filteredFlights;

    public EarthTimeMoreThenTwoHours(List<Flight> flights) {
        this.flights = flights;
    }

    boolean earthTime(List<Segment> seg){
        AtomicLong ai = new AtomicLong(seg.get(0).getArrivalDate().getHour());

        Long sum = seg.stream()
                .skip(1)
                .mapToLong(p -> p.getDepartureDate().minusHours(ai.getAndSet(p.getArrivalDate().getHour())).getHour())
                //.mapToLong(f -> ChronoUnit.HOURS.between(f.getDepartureDate(), f.getArrivalDate()))
                .sum();

        return sum <= 2;
    }

    private void FilteredFlights() {
        System.out.println("Rule: Total time spent on the ground exceeds two hours");

        filteredFlights = flights.stream()
                .map(p -> p.getSegments())
                .filter(p -> p.size() == 1 || earthTime(p))
                .map(p ->new Flight(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFilteredFlights() {
        FilteredFlights();
        return filteredFlights;
    }
}
