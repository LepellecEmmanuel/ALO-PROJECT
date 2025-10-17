package alo.cartaylor.project.v1.api;

import java.util.Set;

/**
 * @author Emmanuel LEPELLEC
 */
public interface CompatibilityChecker {
    /**
     *
     * @param reference
     * @throws IllegalArgumentException if reference is null or not an instance of PartTypeImpl
     * @return all partTypes that are incompatibles with reference
     */
    Set<PartType> getIncompatibilities(PartType reference) throws IllegalArgumentException;

    /**
     *
     * @param reference
     * @throws IllegalArgumentException if reference is null or not an instance of PartTypeImpl
     * @return all parTypes that must be chosen alongside reference
     */
    Set<PartType> getRequirements(PartType reference) throws IllegalArgumentException;
}
