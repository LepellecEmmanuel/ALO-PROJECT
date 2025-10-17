package alo.cartaylor.project.v1.api;

import java.util.Set;

public interface CompatibilityManager extends CompatibilityChecker {
    void addIncompatibilities(PartType reference, Set<PartType> target);
    void addRequirements(PartType reference, Set<PartType> target);
    void removeIncompatibility(PartType reference, PartType target);
    void removeRequirement(PartType reference, PartType target);
}
