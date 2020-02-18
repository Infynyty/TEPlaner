package com.github.infynyty.logic.lessons;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.HashMap;

@Root
public class Lesson implements Serializable {

    @Element
    private int estimatedTime;
    @ElementMap(required = false)
    private HashMap<String, LessonStep> lessonStepsByName = new HashMap<>();
    @Attribute
    private String name;
    @Element(required = false)
    private String comment;
    public static HashMap<String, Lesson> lessonByName = new HashMap<>();

    //TODO: Add to all lessons
    public Lesson() {
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public HashMap<String, LessonStep> getLessonSteps() {
        return lessonStepsByName;
    }

    public void setLessonStepsByName(HashMap<String, LessonStep> lessonStepsByName) {
        this.lessonStepsByName = lessonStepsByName;
    }

    public HashMap<String, LessonStep> getLessonStepsByName() {
        return lessonStepsByName;
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

    public static HashMap<String, Lesson> getLessonByName() {
        return lessonByName;
    }


}
