package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;

public class CategoryImpl implements Category {
    private final String name;

    protected CategoryImpl(String name) {
        // Précondition : le nom ne doit pas être nul ou vide
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        this.name = name.trim();
    }

    @Override
    public String getName() {
        return name;
    }
}