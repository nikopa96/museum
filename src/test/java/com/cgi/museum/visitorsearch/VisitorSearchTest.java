package com.cgi.museum.visitorsearch;

import com.cgi.museum.entities.TimePoint;
import com.cgi.museum.entities.Visit;
import com.cgi.museum.filereader.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VisitorSearchTest {

    private FileReader fileReader;
    private VisitorSearch visitorSearch;

    @BeforeEach
    void setUp() {
        this.fileReader = mock(FileReader.class);
        this.visitorSearch = new VisitorSearch(fileReader);
    }

    @Test
    void getVisitsFromFileLinesTest() throws IOException {
        List<String> visitLines = Arrays.asList("09:40,10:10", "10:00,10:30", "10:10,10:40", "10:20,10:50");
        when(fileReader.getLines()).thenReturn(visitLines);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(LocalTime.parse("09:40", timeFormatter), LocalTime.parse("10:10", timeFormatter)));
        visits.add(new Visit(LocalTime.parse("10:00", timeFormatter), LocalTime.parse("10:30", timeFormatter)));
        visits.add(new Visit(LocalTime.parse("10:10", timeFormatter), LocalTime.parse("10:40", timeFormatter)));
        visits.add(new Visit(LocalTime.parse("10:20", timeFormatter), LocalTime.parse("10:50", timeFormatter)));

        assertEquals(visits, visitorSearch.getVisitsFromFileLines());
    }

    @Test
    void getTimePointsTest() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<Visit> visits = new ArrayList<>();
        visits.add(new Visit(LocalTime.parse("09:40", timeFormatter), LocalTime.parse("10:10", timeFormatter)));
        visits.add(new Visit(LocalTime.parse("10:00", timeFormatter), LocalTime.parse("10:30", timeFormatter)));
        visits.add(new Visit(LocalTime.parse("10:10", timeFormatter), LocalTime.parse("10:30", timeFormatter)));

        assertEquals(4, visitorSearch.getTimePoints(visits).size());
    }

    @Test
    void getTimePointsWithMaxNumberOfVisitorsTest() throws IOException {
        List<String> visitLines = Arrays.asList("09:40,10:10", "10:00,10:30", "10:10,10:40", "10:20,10:50");
        when(fileReader.getLines()).thenReturn(visitLines);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<TimePoint> timePointWithMaxNumberOfVisitors = new ArrayList<>();
        timePointWithMaxNumberOfVisitors.add(new TimePoint(LocalTime.parse("10:10", timeFormatter), 3));
        timePointWithMaxNumberOfVisitors.add(new TimePoint(LocalTime.parse("10:20", timeFormatter), 3));
        timePointWithMaxNumberOfVisitors.add(new TimePoint(LocalTime.parse("10:30", timeFormatter), 3));

        assertEquals(timePointWithMaxNumberOfVisitors, visitorSearch.getTimePointsWithMaxNumberOfVisitors());
    }

    @Test
    void getTimeIntervalWithMaxNumberOfVisitorsTest() throws IOException {
        List<String> visitLines1 = Arrays.asList("09:40,10:10", "10:00,10:30", "10:10,10:40", "10:20,10:50");
        when(fileReader.getLines()).thenReturn(visitLines1);

        assertEquals("10:10-10:30;3", visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());

        List<String> visitLines2 = Arrays.asList("09:40,10:10", "10:10,10:30", "10:10,10:20");
        when(fileReader.getLines()).thenReturn(visitLines2);

        assertEquals("10:10-10:10;3", visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());
    }
}