package com.github.infynyty.logic.calendar;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;
@Root
public abstract class CalendarEvent {
    @Element(name = "date")
    private Date date;
    @Element(name = "name")
    private String name;

    public CalendarEvent(@Element(name = "date") Date date, @Element(name = "name") String name) {
        this.date = date;
        this.name = name;

    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
