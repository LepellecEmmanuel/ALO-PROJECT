package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;

import java.util.*;

public class CompatibilityManagerImpl implements CompatibilityManager {

    private final Map<PartType, Set<PartType>> incompatibilities = new HashMap<>();
    private final Map<PartType, Set<PartType>> requirements = new HashMap<>();

    private void checkPartType(PartType reference) {
        if (reference == null) {
            throw new IllegalArgumentException("Reference cannot be null");
        }
        if (!(reference instanceof PartTypeImpl)) {
            throw new IllegalArgumentException("Reference must be a PartTypeImpl");
        }
    }

    private void checkTargetSet(Set<PartType> target) {
        if (target == null) {
            throw new IllegalArgumentException("Target set cannot be null");
        }
        for (PartType pt : target) {
            if (pt == null) {
                throw new IllegalArgumentException("Target set cannot contain null");
            }
            if (!(pt instanceof PartTypeImpl)) {
                throw new IllegalArgumentException("Target set must contain only PartTypeImpl");
            }
        }
    }

    @Override
    public synchronized Set<PartType> getIncompatibilities(PartType reference) {
        checkPartType(reference);
        return Collections.unmodifiableSet(incompatibilities.getOrDefault(reference, Collections.emptySet()));
    }

    @Override
    public synchronized Set<PartType> getRequirements(PartType reference) {
        checkPartType(reference);
        return Collections.unmodifiableSet(requirements.getOrDefault(reference, Collections.emptySet()));
    }

    @Override
    public synchronized void addIncompatibilities(PartType reference, Set<PartType> target) {
        checkPartType(reference);
        checkTargetSet(target);
        incompatibilities.computeIfAbsent(reference, k -> new HashSet<>()).addAll(target);
    }

    @Override
    public synchronized void addRequirements(PartType reference, Set<PartType> target) {
        checkPartType(reference);
        checkTargetSet(target);
        requirements.computeIfAbsent(reference, k -> new HashSet<>()).addAll(target);
    }

    @Override
    public synchronized void removeIncompatibility(PartType reference, PartType target) {
        checkPartType(reference);
        checkPartType(target);
        Set<PartType> set = incompatibilities.get(reference);
        if (set != null) {
            set.remove(target);
            if (set.isEmpty()) incompatibilities.remove(reference);
        }
    }

    @Override
    public synchronized void removeRequirement(PartType reference, PartType target) {
        checkPartType(reference);
        checkPartType(target);
        Set<PartType> set = requirements.get(reference);
        if (set != null) {
            set.remove(target);
            if (set.isEmpty()) requirements.remove(reference);
        }
    }
}