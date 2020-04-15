package com.cgi.museum.entities;

import java.time.LocalTime;
import java.util.Objects;

public class Visit {

    private final LocalTime start;
    private final LocalTime end;

    public Visit(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(start, visit.start) &&
                Objects.equals(end, visit.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
