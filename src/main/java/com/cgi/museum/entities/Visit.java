package com.cgi.museum.entities;

import java.time.LocalTime;

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
}
