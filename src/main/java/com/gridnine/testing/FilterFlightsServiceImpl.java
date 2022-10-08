package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса для фильтрации полетов
 * В конструктор передаются все перелеты и текущее время на момент запуска фильтрации перелетов
 * @author Albina Safina
 */
public class FilterFlightsServiceImpl implements FilterFlightService{
    private List<Flight> flights;
    private LocalDateTime currentTime;

    public FilterFlightsServiceImpl(List<Flight> flights, LocalDateTime currentTime) {
        this.flights = flights;
        this.currentTime = currentTime;
    }

    @Override
    public void departureDateBeforeCurrTime(){
        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);
        departureDateBeforeCurrTime.show();
    }

    @Override
    public void arriveSegmentsEarlierDeparture(){
        ArriveSegmentsEarlierDeparture arriveSegmentsEarlierDeparture = new ArriveSegmentsEarlierDeparture(flights);
        arriveSegmentsEarlierDeparture.show();
    }

    @Override
    public void earthTimeMoreThenTwoHours(){
        EarthTimeMoreThenTwoHours earthTimeMoreThenTwoHours = new EarthTimeMoreThenTwoHours(flights);
        earthTimeMoreThenTwoHours.show();
    }

    @Override
    public void applyAllFilters() {
        System.out.println("\nApply all filters:");

        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);
        List<Flight> filtered = departureDateBeforeCurrTime.getFilteredFlights();

        ArriveSegmentsEarlierDeparture arriveSegmentsEarlierDeparture = new ArriveSegmentsEarlierDeparture(filtered);
        filtered = arriveSegmentsEarlierDeparture.getFilteredFlights();

        EarthTimeMoreThenTwoHours earthTimeMoreThenTwoHours = new EarthTimeMoreThenTwoHours(filtered);
        earthTimeMoreThenTwoHours.show();
    }
}
