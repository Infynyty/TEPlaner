package com.github.infynyty.logic.calendar;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;
@Root
public abstract class CalendarEvent implements Comparable<CalendarEvent>{
    @Element(name = "date")
    private Date date;
    @Element(name = "name")
    private String name;

    public CalendarEvent() {}


    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(CalendarEvent calendarEvent) {
        return this.date.compareTo(calendarEvent.date);
    }
}
