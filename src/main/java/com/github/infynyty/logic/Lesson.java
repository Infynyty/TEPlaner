package com.github.infynyty.logic;

import org.simpleframework.xml.*;

import java.io.Serializable;
import java.util.ArrayList;

@Root
public class Lesson implements Serializable {

    @Element
    private int estimatedTime;
    @ElementList(required = false)
    private ArrayList<LessonStep> lessonSteps = new ArrayList<>();
    @Attribute
    private String name;
    @Element(required = false)
    private String comment;
    public static ArrayList<Lesson> allLessons = new ArrayList<>();

    //TODO: Add to all lessons
    public Lesson() {
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public ArrayList<LessonStep> getLessonSteps() {
        return lessonSteps;
    }

    public void setLessonSteps(ArrayList<LessonStep> lessonSteps) {
        this.lessonSteps = lessonSteps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
