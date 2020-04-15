package com.cgi.museum.visitorsearch;

import com.cgi.museum.entities.TimePoint;
import com.cgi.museum.entities.Visit;
import com.cgi.museum.filereader.FileReader;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class VisitorSearch {

    private final FileReader fileReader;

    public VisitorSearch(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    private List<Visit> getVisitsFromFileLines() throws IOException {
        List<String> visitLines = fileReader.getLines();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<Visit> visits = new ArrayList<>();

        for (String visitLine : visitLines) {
            String[] visitStartAndEnd = visitLine.split(",");
            LocalTime visitStart = LocalTime.parse(visitStartAndEnd[0], timeFormatter);
            LocalTime visitEnd = LocalTime.parse(visitStartAndEnd[1], timeFormatter);

            Visit visit = new Visit(visitStart, visitEnd);
            visits.add(visit);
        }

        return visits;
    }

    private List<TimePoint> getTimePoints(List<Visit> visits) {
        List<LocalTime> times = new ArrayList<>();

        for (Visit visit : visits) {
            times.add(visit.getStart());
            times.add(visit.getEnd());
        }

        times = times.stream().distinct().collect(Collectors.toList());

        List<TimePoint> timePoints = new ArrayList<>();
        times.forEach(time -> timePoints.add(new TimePoint(time)));

        return timePoints;
    }

    private List<TimePoint> countNumberOfVisitorsForEachTimePoint() throws IOException {
        List<Visit> visits = getVisitsFromFileLines();
        List<TimePoint> timePoints = getTimePoints(visits);

        for (Visit visit : visits) {
            for (TimePoint timePoint : timePoints) {
                if ((timePoint.getTime().compareTo(visit.getStart()) == 0 || timePoint.getTime().isAfter(visit.getStart()))
                        && (timePoint.getTime().compareTo(visit.getEnd()) == 0 || timePoint.getTime().isBefore(visit.getEnd()))) {
                    timePoint.incrementNumberOfVisitors();
                }
            }
        }

        return timePoints;
    }

    private List<TimePoint> getTimePointsWithMaxNumberOfVisitors() throws IOException {
        List<TimePoint> timePoints = countNumberOfVisitorsForEachTimePoint();

        List<TimePoint> timePointsSortedByAscending = timePoints.stream()
                .sorted(Comparator.comparing(TimePoint::getTime))
                .collect(Collectors.toList());

        TimePoint timePointWithMaxNumberOfVisitors = Collections.max(timePointsSortedByAscending,
                Comparator.comparing(TimePoint::getNumberOfVisitors));

        List<TimePoint> timePointsWithMaxNumberOfVisitors = new ArrayList<>();

        int indexOfTimePointWithMaxVisitors = timePointsSortedByAscending.indexOf(timePointWithMaxNumberOfVisitors);
        ListIterator<TimePoint> iterator = timePointsSortedByAscending.listIterator(indexOfTimePointWithMaxVisitors);
        while (iterator.hasNext()) {
            TimePoint currentTimePoint = iterator.next();
            if (currentTimePoint.getNumberOfVisitors() < timePointWithMaxNumberOfVisitors.getNumberOfVisitors()) {
                break;
            }
            timePointsWithMaxNumberOfVisitors.add(currentTimePoint);
        }

        return timePointsWithMaxNumberOfVisitors;
    }

    public String getTimeIntervalWithMaxNumberOfVisitors() throws IOException {
        List<TimePoint> timePointsWithMaxNumberOfVisitors = getTimePointsWithMaxNumberOfVisitors();

        if (timePointsWithMaxNumberOfVisitors.size() == 1) {
            return timePointsWithMaxNumberOfVisitors.get(0).getTime() + "-"
                    + timePointsWithMaxNumberOfVisitors.get(0).getTime()
                    + ";" + timePointsWithMaxNumberOfVisitors.get(0).getNumberOfVisitors();
        } else {
            return timePointsWithMaxNumberOfVisitors.get(0).getTime() + "-"
                    + timePointsWithMaxNumberOfVisitors.get(timePointsWithMaxNumberOfVisitors.size() - 1).getTime()
                    + ";" + timePointsWithMaxNumberOfVisitors.get(0).getNumberOfVisitors();
        }
    }
}
