package alo.cartaylor.project.v1.api;

import java.util.Set;

public interface CompatibilityManager extends CompatibilityChecker {
    /**
     *
     * @param reference
     * @param target
     * @throws IllegalArgumentException if reference is null
     * @throws IllegalArgumentException if reference is in target
     * @throws IllegalArgumentException if some elements in target are in reference requirements
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);
    void addRequirements(PartType reference, Set<PartType> target);
    void removeIncompatibility(PartType reference, PartType target);
    void removeRequirement(PartType reference, PartType target);
}
