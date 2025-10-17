package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class CompatibilityManagerTest {
    private CompatibilityManager manager;
    private List<PartType> partTypes;

    @BeforeEach
    public void setUp() {
        manager = new CompatibilityManagerImpl();
        partTypes = List.of(PartTypeFactory.generate().toArray(new PartType[0]));
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalArgumentExceptionIfReferenceIsNull() {
        PartType reference = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.getIncompatibilities(reference);
        });
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalArgumentExceptionIfReferenceIsInTarget() {
        PartType reference = partTypes.getFirst();
        Set<PartType> target = Set.copyOf(partTypes);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.addIncompatibilities(reference, target);
        });
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalArgumentExceptionIfTargetNotDisjointWithTargetRequirements() {
        PartType reference = partTypes.getFirst();
        PartType target = partTypes.get(1);
        manager.addRequirements(reference, Set.of(target));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.addIncompatibilities(reference, Set.of(target));
        });
    }

    @Test
    public void testThatAddIncompatibilitiesUpdatesTargetIncompatibilities() throws IllegalAccessException {
        PartType reference = partTypes.getFirst();
        Set<PartType> target = Set.copyOf(partTypes.subList(1, 3));
        manager.addIncompatibilities(reference, target);
        Assertions.assertEquals(target, manager.getIncompatibilities(reference));
    }

    @Test
    public void testThatAddRequirementsUpdatesTargetRequirements() throws IllegalAccessException {
        PartType reference = partTypes.getFirst();
        Set<PartType> target = Set.copyOf(partTypes.subList(1, 3));
        manager.addRequirements(reference, target);
        Assertions.assertEquals(target, manager.getRequirements(reference));
    }

    @Test
    public void testThatRemoveIncompatibilityThrowsIllegalArgumentExceptionIfTargetNotInReferenceIncompatibilities() {
        PartType reference = partTypes.getFirst();
        PartType target = partTypes.get(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.removeIncompatibility(reference, target);
        });
    }

    @Test
    public void testThatRemoveRequirementThrowsIllegalArgumentExceptionIfTargetNotInReferenceRequirements() {
        PartType reference = partTypes.getFirst();
        PartType target = partTypes.get(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.removeRequirement(reference, target);
        });
    }

    @Test
    public void testThatRemoveIncompatibilityUpdatesTargetIncompatibilities() throws IllegalAccessException {
        PartType reference = partTypes.getFirst();
        Set<PartType> target = Set.copyOf(partTypes.subList(1, 3));
        manager.addIncompatibilities(reference, target);
        manager.removeIncompatibility(reference, partTypes.get(1));
        Assertions.assertEquals(Set.of(partTypes.get(2)), manager.getIncompatibilities(reference));
    }

    @Test
    public void testThatRemoveRequirementUpdatesTargetRequirement() throws IllegalAccessException {
        PartType reference = partTypes.getFirst();
        Set<PartType> target = Set.copyOf(partTypes.subList(1, 3));
        manager.addRequirements(reference, target);
        manager.removeRequirement(reference, partTypes.get(1));
        Assertions.assertEquals(Set.of(partTypes.get(2)), manager.getRequirements(reference));
    }

    @Test
    public void testThatAddIncompatibilitiesCanNotGenerateRedundantIncompatibilities() throws IllegalAccessException {
        manager.addIncompatibilities(partTypes.getFirst(), Set.of(partTypes.get(1)));
        manager.addIncompatibilities(partTypes.get(1), Set.of(partTypes.getFirst()));
        Assertions.assertEquals(Set.of(partTypes.get(1)), manager.getIncompatibilities(partTypes.getFirst()));
        Assertions.assertEquals(Set.of(), manager.getIncompatibilities(partTypes.get(1)));
    }

    @Test
    public void testThatAddRequirementsCanNotGenerateRedundantRequirements() throws IllegalAccessException {
        manager.addRequirements(partTypes.getFirst(), Set.of(partTypes.get(1)));
        manager.addRequirements(partTypes.get(1), Set.of(partTypes.get(2)));
        manager.addRequirements(partTypes.getFirst(), Set.of(partTypes.get(2)));
        Assertions.assertEquals(Set.of(partTypes.get(2)), manager.getRequirements(partTypes.get(1)));
        Assertions.assertEquals(Set.of(partTypes.get(1)), manager.getRequirements(partTypes.getFirst()));
    }

    @Test
    public void testThatAddRequirementsCanNotCreateCyclesInRequirements() throws IllegalAccessException {
        manager.addRequirements(partTypes.get(1), Set.of(partTypes.get(2)));
        manager.addRequirements(partTypes.get(2), Set.of(partTypes.getFirst()));
        manager.addRequirements(partTypes.getFirst(), Set.of(partTypes.get(1), partTypes.get(2)));
        Assertions.assertEquals(Set.of(),  manager.getRequirements(partTypes.getFirst()));
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalArgumentExceptionIfTargetAnImpliciteReferenceRequirement() {
        manager.addRequirements(partTypes.getFirst(), Set.of(partTypes.get(1)));
        manager.addRequirements(partTypes.get(1), Set.of(partTypes.get(2)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.addIncompatibilities(partTypes.getFirst(), Set.of(partTypes.get(2)));
        });
    }
}
