package com.cgi.museum.entities;

import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "TimePoints{" +
                "time=" + time +
                ", numberOfVisitors=" + numberOfVisitors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePoint timePoint = (TimePoint) o;
        return numberOfVisitors == timePoint.numberOfVisitors &&
                time.equals(timePoint.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, numberOfVisitors);
    }
}
