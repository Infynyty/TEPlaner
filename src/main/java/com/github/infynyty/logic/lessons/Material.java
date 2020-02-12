package com.github.infynyty.logic.lessons;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.HashSet;

@Root
public class Material {

    @Attribute
    private String name;
    @Element
    private int amount;
    private static HashSet<Material> allMaterials = new HashSet<Material>();
    private static HashMap<String, Material> materialByName = new HashMap<>();

    //Todo: Add to all materials
    public Material() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public HashSet<Material> getAllMaterials() {
        return allMaterials;
    }

    public void setAllMaterials(HashSet<Material> allMaterials) {
        this.allMaterials = allMaterials;
    }

    public static HashMap<String, Material> getMaterialByName() {
        return materialByName;
    }
}
