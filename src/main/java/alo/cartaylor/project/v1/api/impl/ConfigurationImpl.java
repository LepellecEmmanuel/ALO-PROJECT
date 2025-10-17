package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.PartType;

import java.util.Set;

public class ConfigurationImpl implements Configuration {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return Set.of();
    }

    @Override
    public void selectPart(PartType chosenPart) {

    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        return null;
    }

    @Override
    public void unselectPartType(Category category) {

    }

    @Override
    public void clear() {

    }
}
