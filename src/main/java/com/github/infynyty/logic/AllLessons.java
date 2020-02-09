package com.github.infynyty.logic;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class AllLessons {

    @ElementList
    private ArrayList<Lesson> allLessonsList = new ArrayList<>();

    private static AllLessons instance;

    public AllLessons() {
    }

    public ArrayList<Lesson> getAllLessonsList() {
        return allLessonsList;
    }

    public void setAllLessonsList(ArrayList<Lesson> allLessonsList) {
        this.allLessonsList = allLessonsList;
    }

    public static AllLessons getInstance() {
        return instance == null ? instance = new AllLessons() : instance;
    }

    public static void setInstance(AllLessons instance) {
        AllLessons.instance = instance;
    }
}
