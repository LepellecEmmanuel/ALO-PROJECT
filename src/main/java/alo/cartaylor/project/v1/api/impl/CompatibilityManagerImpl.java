package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;

import java.util.*;
import java.util.stream.Collectors;

public class CompatibilityManagerImpl implements CompatibilityManager {

    private final Map<PartType, Set<PartType>> incompatibilities = new HashMap<>();
    private final Map<PartType, Set<PartType>> requirements = new HashMap<>();

    private void checkThatReferenceIsValid(PartType reference) {
        if (reference == null) {
            throw new IllegalArgumentException("reference is required!");
        }
        if (!(reference instanceof PartTypeImpl)) {
            throw new IllegalArgumentException("invalid reference type!");
        }
    }

    private void checkThatTargetIsValid(Set<PartType> target, PartType reference) {
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("target is required!");
        }
        if (target.contains(reference)) {
            throw new IllegalArgumentException("reference cannot be contained in target!");
        }
    }

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        checkThatReferenceIsValid(reference);
        return Collections.unmodifiableSet(incompatibilities.getOrDefault(reference, Set.of()));
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        checkThatReferenceIsValid(reference);
        return Collections.unmodifiableSet(requirements.getOrDefault(reference, Set.of()));
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {
        checkThatReferenceIsValid(reference);
        checkThatTargetIsValid(target, reference);
        if(!Collections.disjoint(getRequirementsGraphSet(reference), target)) {
            throw new IllegalArgumentException("target incompatible with reference requirements!");
        }
        incompatibilities.computeIfAbsent(reference, __ -> new HashSet<>())
                .addAll(target.stream().filter(partType -> !incompatibilities.getOrDefault(partType, Set.of()).contains(reference)).collect(Collectors.toSet()));
    }

    private Set<PartType> getRequirementsGraphSet(PartType reference) {
        Set<PartType> visited = new HashSet<>();
        collectRequirements(reference, visited);
        return Collections.unmodifiableSet(visited);
    }

    private void collectRequirements(PartType current, Set<PartType> visited) {
        for (PartType requirement : requirements.getOrDefault(current, Set.of())) {
            if (visited.add(requirement)) {
                collectRequirements(requirement, visited);
            }
        }
    }

    private boolean addingReferenceDoNotCreateCycleInPartTypeRequirementsGraph(PartType reference, PartType partType) {
        return !getRequirementsGraphSet(partType).contains(reference);
    }

    @Override
    public  void addRequirements(PartType reference, Set<PartType> target) {
        checkThatReferenceIsValid(reference);
        checkThatTargetIsValid(target, reference);
        if(!Collections.disjoint(incompatibilities.getOrDefault(reference, Set.of()), target)) {
            throw new IllegalArgumentException("target incompatible with reference incompatibilities!");
        }
        requirements.computeIfAbsent(reference, __ -> new HashSet<>())
                .addAll(target.stream().filter(partType ->
                        !getRequirementsGraphSet(reference).contains(partType) && addingReferenceDoNotCreateCycleInPartTypeRequirementsGraph(reference, partType))
                        .collect(Collectors.toSet())
                );
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        checkThatReferenceIsValid(reference);
        checkThatTargetIsValid(Set.of(target), reference);
        Set<PartType> set = incompatibilities.get(reference);
        if(!incompatibilities.getOrDefault(reference, Set.of()).contains(target)) {
            throw new IllegalArgumentException("target not present in reference incompatibilities!");
        }
        incompatibilities.get(reference).remove(target);
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        checkThatReferenceIsValid(reference);
        checkThatTargetIsValid(Set.of(target), reference);
        Set<PartType> set = requirements.get(reference);
        if(!requirements.getOrDefault(reference, Set.of()).contains(target)) {
            throw new IllegalArgumentException("target not present in reference requirements!");
        }
        requirements.get(reference).remove(target);
    }
}