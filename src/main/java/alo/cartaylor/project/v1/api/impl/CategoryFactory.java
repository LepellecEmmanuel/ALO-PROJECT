package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CategoryFactory {
    public static Set<Category> generate() {
        Set<Category> categories = new HashSet<Category>();
        categories.add(new CategoryImpl("Engine"));
        categories.add(new CategoryImpl("Transmission"));
        categories.add(new CategoryImpl("Exterior"));
        categories.add(new CategoryImpl("Interior"));
        return Collections.unmodifiableSet(categories);
    }
}
