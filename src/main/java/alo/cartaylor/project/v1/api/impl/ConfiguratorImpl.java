package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;

import java.util.*;


public class ConfiguratorImpl implements Configurator {
    private final Set<Category> categories;
    private final Map<Category, Set<PartType>> variants;
    private final Configuration configuration;
    private final CompatibilityChecker compatibilityChecker;

    protected ConfiguratorImpl(Set<Category> categories,
                            Map<Category, Set<PartType>> variants,
                            Configuration configuration,
                            CompatibilityChecker compatibilityChecker) {
        if (categories == null || variants == null || configuration == null || compatibilityChecker == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.categories = Set.copyOf(categories);
        this.variants = new HashMap<>();
        for (Map.Entry<Category, Set<PartType>> entry : variants.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Category and variants cannot be null");
            }
            this.variants.put(entry.getKey(), Set.copyOf(entry.getValue()));
        }
        this.configuration = configuration;
        this.compatibilityChecker = compatibilityChecker;
    }

    @Override
    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return variants.getOrDefault(category, Set.of());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return compatibilityChecker;
    }
}