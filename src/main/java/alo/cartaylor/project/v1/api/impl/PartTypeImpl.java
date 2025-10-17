package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.PartType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PartTypeImpl implements PartType {
    private final String name;
    private final Category category;

    protected PartTypeImpl(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }
}
