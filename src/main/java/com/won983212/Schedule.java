package com.won983212;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private final String title;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Schedule(String title, LocalDate fromDate, LocalDate toDate) {
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return String.format("Schedule[title=%s, from=%s, to=%s]", title, fromDate, toDate);
    }

    public static Schedule parseFromJSON(JSONObject obj) {
        String title = obj.get("taskName").toString();
        LocalDate fromDate = LocalDate.parse(obj.get("startDt").toString(), DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate toDate = LocalDate.parse(obj.get("endDt").toString(), DateTimeFormatter.ofPattern("yyyyMMdd"));
        return new Schedule(title, fromDate, toDate);
    }
}
