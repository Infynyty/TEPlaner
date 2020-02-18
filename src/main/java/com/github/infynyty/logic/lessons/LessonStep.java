package com.github.infynyty.logic.lessons;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root
public class LessonStep implements Comparable<LessonStep>{

    @Element
    private int estimatedTime;
    @ElementList
    private ArrayList<Material> materials = new ArrayList<Material>();
    @Attribute
    private String name;
    @Element
    private boolean groupNeeded = false;
    @Element
    private int index;

    public LessonStep() {}

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGroupNeeded() {
        return groupNeeded;
    }

    public void setGroupNeeded(boolean groupNeeded) {
        this.groupNeeded = groupNeeded;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(LessonStep lessonStep) {
        return this.index - lessonStep.index;
    }
}
