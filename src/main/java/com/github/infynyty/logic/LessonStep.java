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
}
