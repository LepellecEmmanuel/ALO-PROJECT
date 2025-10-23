package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConfiguratorImpl implements Configurator {
    private final Set<Category> categories = CategoryFactory.generate();
    private final Set<PartType> partTypes = PartTypeFactory.generate();
    private final CompatibilityChecker checker = initCompatibilityChecker();
    private final Configuration configuration = new ConfigurationImpl(checker, categories);

    private PartType getPartType(String name) {
        return partTypes.stream().filter(partType ->  partType.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Part type not found!"));
    }

    private CompatibilityChecker initCompatibilityChecker() {
        CompatibilityManager manager = new CompatibilityManagerImpl();
        manager.addRequirements(getPartType("EH120"), Set.of(getPartType("TC120")));
        manager.addRequirements(getPartType("TC120"), Set.of(getPartType("EH120")));
        manager.addRequirements(getPartType("XS"), Set.of(getPartType("IS")));
        manager.addRequirements(getPartType("IS"), Set.of(getPartType("XS")));
        manager.addIncompatibilities(getPartType("TA5"), Set.of(getPartType("EG100")));
        manager.addIncompatibilities(getPartType("TSF7"), Set.of(getPartType("EG100"), getPartType("EG133"), getPartType("ED110")));
        manager.addIncompatibilities(getPartType("XC"), Set.of(getPartType("EG210")));
        manager.addIncompatibilities(getPartType("XM"), Set.of(getPartType("EG100")));
        manager.addIncompatibilities(getPartType("XS"), Set.of(getPartType("EG100")));
        manager.addIncompatibilities(getPartType("IS"), Set.of(getPartType("EG100"), getPartType("TM5")));
        return (CompatibilityChecker) manager;
    }

    @Override
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category is required!");
        }
        if(!(category instanceof CategoryImpl)) {
            throw new IllegalArgumentException("Category provided is not valid!");
        }
        return partTypes.stream().filter(partType -> partType.getCategory().equals(category)).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return checker;
    }
}