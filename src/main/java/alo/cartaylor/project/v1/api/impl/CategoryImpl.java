package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryImpl category = (CategoryImpl) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
