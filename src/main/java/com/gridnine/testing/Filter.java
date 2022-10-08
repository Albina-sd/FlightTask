package com.gridnine.testing;

import java.util.List;

public interface Filter {
    List<Flight> getFilteredFlights();

    default void show(){
        getFilteredFlights().stream()
                .map(o -> o.getSegments())
                .forEach((p) -> System.out.println(p));
    }
}
