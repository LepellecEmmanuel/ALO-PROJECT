package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;

import java.util.*;
import java.util.stream.Collectors;


public class ConfiguratorImpl implements Configurator {
    private final Set<Category> categories = CategoryFactory.generate();
    private final Set<PartType> partTypes = PartTypeFactory.generate();
    private final Configuration configuration = new ConfigurationImpl();
    private final CompatibilityChecker compatibilityChecker = new CompatibilityManagerImpl();

    @Override
    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return partTypes.stream().filter(partType -> partType.getCategory().equals(category)).collect(Collectors.toSet());
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