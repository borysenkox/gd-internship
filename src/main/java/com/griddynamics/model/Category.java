package com.griddynamics.model;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;

    private String name;

    private Category parentCategory;

    private boolean isTopBranch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
