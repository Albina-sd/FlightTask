package com.gridnine.testing;

public interface FilterFlightService {

    void departureDateBeforeCurrTime();

    void arriveSegmentsEarlierDeparture();

    void earthTimeMoreThenTwoHours();

    void applyAllFilters();
}
