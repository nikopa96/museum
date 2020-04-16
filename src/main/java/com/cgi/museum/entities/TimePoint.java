package com.cgi.museum.entities;

import java.time.LocalTime;

public class TimePoint {

    private final LocalTime time;
    private int numberOfVisitors;

    public TimePoint(LocalTime time) {
        this.time = time;
    }

    public TimePoint(LocalTime time, int numberOfVisitors) {
        this.time = time;
        this.numberOfVisitors = numberOfVisitors;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void incrementNumberOfVisitors() {
        this.numberOfVisitors++;
    }
}
