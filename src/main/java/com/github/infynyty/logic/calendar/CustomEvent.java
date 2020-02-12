package com.github.infynyty.logic.calendar;

import org.simpleframework.xml.Element;

import java.util.Date;

public class CustomEvent extends CalendarEvent {
    @Element(name = "description")
    private String description;

    public CustomEvent(Date date, String name, @Element(name = "description") String description) {
        super(date, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
