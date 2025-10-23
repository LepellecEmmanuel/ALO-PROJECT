package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.PartType;

import java.util.*;

public class ConfigurationImpl implements Configuration {
    private CompatibilityChecker checker;
    private Set<Category> categories;

    protected ConfigurationImpl(CompatibilityChecker checker, Set<Category> categories) {
        this.checker = checker;
        this.categories = categories;
    }

    private final Map<Category, PartType> selections = new HashMap<>();

    private void checkThatCategoryProvidedIsValid(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

    }

    private void checkThatPartTypeProvidedIsValid(PartType partType) {
        if (partType == null) {
            throw new IllegalArgumentException("PartType cannot be null");
        }
        if (!(partType instanceof PartTypeImpl)) {
            throw new IllegalArgumentException("PartType must be a PartTypeImpl");
        }
    }

    @Override
    public boolean isValid() {
        boolean noIncompatibilities = selections.values().stream().allMatch(partType -> Collections.disjoint(checker.getIncompatibilities(partType), selections.values()));
        boolean allRequirements = selections.values().stream().allMatch(partType -> selections.values().containsAll(checker.getRequirements(partType)));
        return noIncompatibilities && allRequirements;
    }

    @Override
    public boolean isCompleted() {
        return selections.keySet().equals(categories);
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return Set.copyOf(selections.values());
    }

    @Override
    public void selectPart(PartType chosenPart) {
        checkThatPartTypeProvidedIsValid(chosenPart);
        Category category = chosenPart.getCategory();
        checkThatCategoryProvidedIsValid(category);
        selections.put(category, chosenPart);
    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        checkThatCategoryProvidedIsValid(category);
        return selections.get(category);
    }

    @Override
    public void unselectPartType(Category category) {
        checkThatCategoryProvidedIsValid(category);
        selections.remove(category);
    }

    @Override
    public void clear() {
        selections.clear();
    }
}