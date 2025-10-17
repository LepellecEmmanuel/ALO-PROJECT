package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CategoryImpl implements Category {
    private final String name;

    protected CategoryImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
