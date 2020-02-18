package com.github.infynyty.logic.calendar;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;

import java.util.HashMap;
@Root
public class Calendar {

    @ElementMap
    private HashMap<String, CalendarEvent> calendarEventByName = new HashMap<>();

    private static Calendar instance;

    public static Calendar getInstance() {
        return instance == null ? instance = new Calendar() : instance;
    }

    public static void setInstance(Calendar instance) {
        Calendar.instance = instance;
    }

    public HashMap<String, CalendarEvent> getCalendarEventByName() {
        return calendarEventByName;
    }
}
