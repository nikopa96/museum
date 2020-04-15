package com.cgi.museum;

import com.cgi.museum.filereader.FileReader;
import com.cgi.museum.visitorsearch.VisitorSearch;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        VisitorSearch visitorSearch = new VisitorSearch(new FileReader(args[0]));
        System.out.println(visitorSearch.getTimeIntervalWithMaxNumberOfVisitors());
    }
}
