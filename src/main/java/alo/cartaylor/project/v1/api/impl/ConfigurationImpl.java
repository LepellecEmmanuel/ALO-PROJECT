package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.PartType;

import java.util.*;

public class ConfigurationImpl implements Configuration {

    private final Map<Category, PartType> selections = new HashMap<>();

    private void checkCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }

    private void checkPartType(PartType partType) {
        if (partType == null) {
            throw new IllegalArgumentException("PartType cannot be null");
        }
        if (!(partType instanceof PartTypeImpl)) {
            throw new IllegalArgumentException("PartType must be a PartTypeImpl");
        }
    }

    @Override
    public boolean isValid() {
        // Validité : pas de doublons et pas de valeurs nulles
        Set<PartType> parts = new HashSet<>(selections.values());
        return parts.size() == selections.size() && !selections.containsValue(null);
    }

    @Override
    public boolean isCompleted() {
        // Complétude : toutes les catégories ont une sélection (adapter si liste globale de catégories)
        return !selections.isEmpty() && !selections.containsValue(null);
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return Collections.unmodifiableSet(new HashSet<>(selections.values()));
    }

    @Override
    public void selectPart(PartType chosenPart) {
        checkPartType(chosenPart);
        Category category = chosenPart.getCategory();
        checkCategory(category);
        selections.put(category, chosenPart);
    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        checkCategory(category);
        return selections.get(category);
    }

    @Override
    public void unselectPartType(Category category) {
        checkCategory(category);
        selections.remove(category);
    }

    @Override
    public void clear() {
        selections.clear();
    }
}