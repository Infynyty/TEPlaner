package com.github.infynyty.logic;

import java.util.ArrayList;

public class LessonStep {

    private int estimatedTime;
    private ArrayList<Material> materials = new ArrayList<Material>();
    private String name;
    private boolean groupNeeded = false;

    public LessonStep(int estimatedTime, ArrayList<Material> materials, String name, boolean groupNeeded) {
        this.estimatedTime = estimatedTime;
        this.materials = materials;
        this.name = name;
        this.groupNeeded = groupNeeded;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public String getName() {
        return name;
    }

    public boolean isGroupNeeded() {
        return groupNeeded;
    }
}
