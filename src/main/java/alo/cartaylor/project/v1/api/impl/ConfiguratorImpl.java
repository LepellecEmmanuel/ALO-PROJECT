package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;

import java.util.Set;

public class ConfiguratorImpl implements Configurator {
    @Override
    public Set<Category> getCategories() {
        return Set.of();
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        return Set.of();
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return null;
    }
}
