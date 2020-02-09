package com.github.infynyty.logic;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Material {

    private String name;
    private int amount;
    private HashSet<Material> allMaterials = new HashSet<Material>();

    public Material(String name, int amount) {
        this.name = name;
        this.amount = amount;
        allMaterials.add(this);
    }

    public void saveToFile(File file) {

    }

    public HashSet<Material> getAllMaterials() {
        return allMaterials;
    }
}
