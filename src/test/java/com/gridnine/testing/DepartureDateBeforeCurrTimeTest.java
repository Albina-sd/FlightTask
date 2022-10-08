package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование функционала {@link com.gridnine.testing.DepartureDateBeforeCurrTime}.
 */

@DisplayName("Тестирование фильтра: вылет до текущего момента времени")
class DepartureDateBeforeCurrTimeTest {

    @Test
    @DisplayName("Тест 1: убрать неверный полет из одного сегмента, должен пройти успешно")
    void deleteOneSingleSegment_Test(){
        //given

        LocalDateTime currentTime = LocalDateTime.now();

        List<Segment> segment1 = new LinkedList<>();
        segment1.add(new Segment(currentTime.plusHours(1), currentTime.plusHours(3)));
        segment1.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));

        String rightDepartureDate = currentTime.plusHours(1).toString();

        List<Segment> segment2 = new LinkedList<>();
        segment2.add(new Segment(currentTime.minusHours(3), currentTime.plusHours(1)));

        Flight flight1 = new Flight(segment1);
        Flight flight2 = new Flight(segment2);

        List<Flight> flights = new LinkedList<>();
        flights.add(flight1);
        flights.add(flight2);

        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);

        //then

        List<Flight> filteredFlights = departureDateBeforeCurrTime.getFilteredFlights();
        assertEquals(1, filteredFlights.size());
        assertEquals(rightDepartureDate, filteredFlights.get(0).getSegments().get(0).getDepartureDate().toString());
    }

    @Test
    @DisplayName("Тест 2: убрать неверный полет состоящий из нескольких сегментов, должен пройти успешно")
    void MultiSegment_Test(){
        //given

        LocalDateTime currentTime = LocalDateTime.now();

        List<Segment> segment1 = new LinkedList<>();
        segment1.add(new Segment(currentTime.plusHours(1), currentTime.plusHours(3)));
        segment1.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));

        String rightDepartureDate = currentTime.plusHours(1).toString();

        List<Segment> segment2 = new LinkedList<>();
        segment2.add(new Segment(currentTime.plusHours(2), currentTime.plusHours(3)));
        segment2.add(new Segment(currentTime.minusHours(3), currentTime.plusHours(1)));
        segment2.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));

        Flight flight1 = new Flight(segment1);
        Flight flight2 = new Flight(segment2);

        List<Flight> flights = new LinkedList<>();
        flights.add(flight1);
        flights.add(flight2);

        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);

        //then

        List<Flight> filteredFlights = departureDateBeforeCurrTime.getFilteredFlights();
        assertEquals(1, filteredFlights.size());
        assertEquals(rightDepartureDate, filteredFlights.get(0).getSegments().get(0).getDepartureDate().toString());
    }

    @Test
    @DisplayName("Тест 3: все полеты нормальные, должен пройти успешно")
    void doingNothing_Test(){

        //given

        LocalDateTime currentTime = LocalDateTime.now();

        List<Segment> segment1 = new LinkedList<>();
        segment1.add(new Segment(currentTime.plusHours(1), currentTime.plusHours(3)));
        segment1.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));

        List<Segment> segment2 = new LinkedList<>();
        segment2.add(new Segment(currentTime.plusHours(2), currentTime.plusHours(3)));
        segment2.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));
        segment2.add(new Segment(currentTime.plusHours(6), currentTime.plusHours(7)));

        List<Segment> segment3 = new LinkedList<>();
        segment3.add(new Segment(currentTime.plusHours(6), currentTime.plusHours(7)));
        String rightDepartureDate = currentTime.plusHours(6).toString();

        Flight flight1 = new Flight(segment1);
        Flight flight2 = new Flight(segment2);
        Flight flight3 = new Flight(segment3);

        List<Flight> flights = new LinkedList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);

        //then

        List<Flight> filteredFlights = departureDateBeforeCurrTime.getFilteredFlights();
        assertEquals(3, filteredFlights.size());
        assertEquals(rightDepartureDate, filteredFlights.get(2).getSegments().get(0).getDepartureDate().toString());
    }

    @Test
    @DisplayName("Тест 4: все полеты не проходят, должен пройти успешно")
    void deleteAll_Test(){

        //given

        LocalDateTime currentTime = LocalDateTime.now();

        List<Segment> segment1 = new LinkedList<>();
        segment1.add(new Segment(currentTime.minusHours(3), currentTime.plusHours(1)));
        segment1.add(new Segment(currentTime.plusHours(4), currentTime.plusHours(5)));

        List<Segment> segment2 = new LinkedList<>();
        segment2.add(new Segment(currentTime.minusHours(4), currentTime.minusHours(1)));

        List<Segment> segment3 = new LinkedList<>();
        segment3.add(new Segment(currentTime.minusHours(8), currentTime.minusHours(7)));

        Flight flight1 = new Flight(segment1);
        Flight flight2 = new Flight(segment2);
        Flight flight3 = new Flight(segment3);

        List<Flight> flights = new LinkedList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        DepartureDateBeforeCurrTime departureDateBeforeCurrTime = new DepartureDateBeforeCurrTime(flights, currentTime);

        //then

        List<Flight> filteredFlights = departureDateBeforeCurrTime.getFilteredFlights();
        assertEquals(0, filteredFlights.size());

    }

}