package com.github.infynyty.logic;

import java.util.ArrayList;

public class Lesson {

    private int estimatedTime;
    private ArrayList<LessonStep> lessonSteps = new ArrayList<>();
    private String name;
    private String comment;

    public Lesson(int estimatedTime, ArrayList<LessonStep> lessonSteps, String name, String comment) {
        this.estimatedTime = estimatedTime;
        this.lessonSteps = lessonSteps;
        this.name = name;
        this.comment = comment;
    }
}
