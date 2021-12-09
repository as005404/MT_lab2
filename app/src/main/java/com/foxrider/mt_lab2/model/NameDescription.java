package com.foxrider.mt_lab2.model;

public class NameDescription {
    String name;
    String description;

    public NameDescription(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public NameDescription() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
