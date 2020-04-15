package com.cgi.museum.visitorsearch;

import com.cgi.museum.filereader.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    @DisplayName("should return the correct interval if the max number of visitors lasted only 1 minute")
    void getCorrectTimeIntervalWithEqualStartAndEnd() throws IOException {
        List<String> visitLines = Arrays.asList("10:10,10:11", "10:11,10:30", "10:11,10:40");
        when(fileReader.getLines()).thenReturn(visitLines);

        assertEquals("10:11-10:11;3", visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());
    }

    @Test
    @DisplayName("should return the correct interval that lasts more than 1 minute")
    void getCorrectTimeInterval() throws IOException {
        List<String> visitLines = Arrays.asList("09:40,10:10", "10:00,10:30", "10:10,10:40", "10:20,10:50");
        when(fileReader.getLines()).thenReturn(visitLines);

        assertEquals("10:10-10:30;3", visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());
    }

    @Test
    @DisplayName("should return the first correct interval if there are several intervals with the max number of visitors")
    void getFirstCorrectIntervalFromSeveral() throws IOException {
        List<String> visitLines = Arrays.asList("09:40,10:10", "10:00,10:30", "10:10,10:40", "10:20,10:50",
                "10:45,10:55", "10:45,10:55", "10:52,11:00");
        when(fileReader.getLines()).thenReturn(visitLines);

        assertEquals("10:10-10:30;3", visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());
    }
}