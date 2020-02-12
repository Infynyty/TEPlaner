package com.github.infynyty.logic.calendar;

import com.github.infynyty.logic.lessons.Lesson;
import org.simpleframework.xml.Element;

import java.util.Date;

public class TrainingEvent extends CalendarEvent {
    @Element(name = "lesson")
    private Lesson lesson;

    public TrainingEvent(Date date, String name, @Element(name = "lesson") Lesson lesson) {
        super(date, name);
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }
}
