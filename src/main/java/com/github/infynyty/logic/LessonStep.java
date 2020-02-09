package com.github.infynyty.logic;

import org.simpleframework.xml.*;

import java.io.Serializable;
import java.util.ArrayList;

@Root
public class LessonStep {

    @Element
    private int estimatedTime;
    @ElementList
    private ArrayList<Material> materials = new ArrayList<Material>();
    @Attribute
    private String name;
    @Element
    private boolean groupNeeded = false;

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
}
