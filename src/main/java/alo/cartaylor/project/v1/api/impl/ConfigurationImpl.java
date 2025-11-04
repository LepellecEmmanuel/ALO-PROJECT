package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.PartType;

import java.util.*;

public class ConfigurationImpl implements Configuration {
    private CompatibilityChecker checker;
    private Set<Category> categories;
    private Set<PartType> partTypes;

    protected ConfigurationImpl(CompatibilityChecker checker, Set<Category> categories,  Set<PartType> partTypes) {
        this.checker = checker;
        this.categories = categories;
        this.partTypes = partTypes;
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

    private boolean withoutIncompatibilitiesChain(PartType partType) {
        Set<PartType> chain = new HashSet<>(checker.getIncompatibilities(partType));
        for (PartType partTypeItem : partTypes) {
            if(checker.getIncompatibilities(partTypeItem).contains(partType)) {
                chain.add(partTypeItem);
            }
        }
        return Collections.disjoint(chain, selections.values());
    }

    private Set<PartType> requirementsChain(PartType partType, PartType stop) {
        if(checker.getRequirements(partType).isEmpty()) {
            return new HashSet<>();
        } else {
            HashSet<PartType> requirements = new HashSet<>(checker.getRequirements(partType));
            for(PartType requirement :  checker.getRequirements(partType)) {
                if(requirement.equals(stop)) {
                    break;
                }
                requirements.addAll(requirementsChain(requirement, stop));
            }
            return requirements;
        }
    }

    @Override
    public boolean isValid() {
        boolean noIncompatibilities = selections.values().stream().allMatch(this::withoutIncompatibilitiesChain);
        boolean allRequirements = selections.values().stream().allMatch(partType -> selections.values().containsAll(requirementsChain(partType, partType)));
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