package com.corona.tracker.coronavirustracker.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocationStats {
    private String state;
    private String country;
    private long latestCases;
    private int diffFromPreviousDay;
}
