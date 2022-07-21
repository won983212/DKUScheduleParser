package com.won983212;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Schedule> scheduleList = new ArrayList<>();

    public void parse(String in) throws ParseException {
        scheduleList.clear();
        JSONParser parser = new JSONParser();

        // parse root json object
        JSONObject obj = (JSONObject) parser.parse(in);
        String data = obj.get("data").toString();

        // parse 'data'
        obj = (JSONObject) parser.parse(data);
        data = (String) ((JSONArray) obj.get("schArr")).get(0);

        // parse 'schArr'
        JSONArray schArr = (JSONArray) parser.parse(data);
        for (Object dateTasksObj : schArr) {
            JSONObject dateTasks = (JSONObject) dateTasksObj;
            JSONArray cttTaskList = (JSONArray) dateTasks.get("cttTaskList");
            for (Object taskObj : cttTaskList) {
                JSONObject task = (JSONObject) taskObj;
                scheduleList.add(Schedule.parseFromJSON(task));
            }
        }
    }

    public String getAsSQL() {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (Schedule schedule : scheduleList) {
            String sqlLine = String.format("insert into `Schedule` values (%d, now(), now(), '%s', '%s', '%s');\n",
                    index++, schedule.getToDate(), schedule.getFromDate(), schedule.getTitle());
            sb.append(sqlLine);
        }
        return sb.toString();
    }
}
