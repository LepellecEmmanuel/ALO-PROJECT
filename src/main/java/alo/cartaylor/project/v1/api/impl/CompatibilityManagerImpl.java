package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;

import java.util.Collections;
import java.util.Set;

public class CompatibilityManagerImpl implements CompatibilityManager {

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) throws IllegalArgumentException {
        if(!(reference instanceof PartTypeImpl)) {
            throw new IllegalArgumentException("Invalid reference");
        }

        return Set.of();
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return Set.of();
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {

    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {

    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {

    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {

    }

}
