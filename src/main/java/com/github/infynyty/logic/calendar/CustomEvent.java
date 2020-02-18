package com.github.infynyty.logic.calendar;

import org.simpleframework.xml.Element;

import java.util.Date;

public class CustomEvent extends CalendarEvent {
    @Element(name = "description")
    private String description;

    public CustomEvent(){
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
