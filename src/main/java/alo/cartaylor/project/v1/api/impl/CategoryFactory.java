package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CategoryFactory {
    private Set<Category> categories = new HashSet<>();
    private void generate() {
        categories.add(new CategoryImpl("Engine"));
        categories.add(new CategoryImpl("Transmission"));
        categories.add(new CategoryImpl("Exterior"));
        categories.add(new CategoryImpl("Interior"));
    }

    public CategoryFactory() {
        generate();
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public Category getCategory(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }
}
