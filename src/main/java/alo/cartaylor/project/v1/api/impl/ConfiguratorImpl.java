package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;

import java.util.*;
import java.util.stream.Collectors;


public class ConfiguratorImpl implements Configurator {
    private final CategoryFactory categoryFactory = new CategoryFactory();
    private final PartTypeFactoryV2 partTypeFactory = new PartTypeFactoryV2();
    private final Set<Category> categories = categoryFactory.getCategories();
    private final Set<PartType> partTypes = partTypeFactory.getPartTypes();
    private final Configuration configuration;
    private final CompatibilityChecker compatibilityChecker;

    public ConfiguratorImpl() {
        this.compatibilityChecker = initCompatibilityChecker();
        this.configuration = new ConfigurationImpl(compatibilityChecker);
    }

    private CompatibilityChecker initCompatibilityChecker() {
        CompatibilityManager manager = new CompatibilityManagerImpl();
        manager.addRequirements(partTypeFactory.getPartType("EH120"), Set.of(partTypeFactory.getPartType("TC120")));
        manager.addRequirements(partTypeFactory.getPartType("TC120"), Set.of(partTypeFactory.getPartType("EH120")));
        manager.addRequirements(partTypeFactory.getPartType("XS"), Set.of(partTypeFactory.getPartType("IS")));
        manager.addRequirements(partTypeFactory.getPartType("IS"), Set.of(partTypeFactory.getPartType("XS")));
        manager.addIncompatibilities(partTypeFactory.getPartType("TA5"), Set.of(partTypeFactory.getPartType("EG100")));
        manager.addIncompatibilities(partTypeFactory.getPartType("TSF7"), Set.of(partTypeFactory.getPartType("EG100"), partTypeFactory.getPartType("EG133"), partTypeFactory.getPartType("EG110")));
        manager.addIncompatibilities(partTypeFactory.getPartType("XC"), Set.of(partTypeFactory.getPartType("EG210")));
        manager.addIncompatibilities(partTypeFactory.getPartType("XM"), Set.of(partTypeFactory.getPartType("EG100")));
        manager.addIncompatibilities(partTypeFactory.getPartType("XS"), Set.of(partTypeFactory.getPartType("EG100")));
        manager.addIncompatibilities(partTypeFactory.getPartType("IS"), Set.of(partTypeFactory.getPartType("EG100"), partTypeFactory.getPartType("TM5")));
        return (CompatibilityChecker) manager;
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