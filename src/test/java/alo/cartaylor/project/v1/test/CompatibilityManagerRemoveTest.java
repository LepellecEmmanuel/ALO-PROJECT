package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Set;

public class CompatibilityManagerRemoveTest {

    private CompatibilityManager manager;
    private PartTypeFactoryV2 factory;
    private PartType pt1, pt2;

    @BeforeEach
    public void setup() {
        manager = new CompatibilityManagerImpl();
        factory = new PartTypeFactoryV2();
        pt1 = factory.getPartType("EG100");
        pt2 = factory.getPartType("TA5");
    }

    @Test
    public void testRemoveIncompatibilityRemovesTarget() throws IllegalAccessException {
        manager.addIncompatibilities(pt1, Set.of(pt2));
        Assertions.assertTrue(manager.getIncompatibilities(pt1).contains(pt2));

        manager.removeIncompatibility(pt1, pt2);
        Assertions.assertFalse(manager.getIncompatibilities(pt1).contains(pt2));
        Assertions.assertTrue(manager.getIncompatibilities(pt1).isEmpty());
    }

    @Test
    public void testRemoveRequirementRemovesTarget() throws IllegalAccessException {
        manager.addRequirements(pt1, Set.of(pt2));
        Assertions.assertTrue(manager.getRequirements(pt1).contains(pt2));

        manager.removeRequirement(pt1, pt2);
        Assertions.assertFalse(manager.getRequirements(pt1).contains(pt2));
        Assertions.assertTrue(manager.getRequirements(pt1).isEmpty());
    }

    @Test
    public void testRemoveNonExistentIncompatibilityDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> manager.removeIncompatibility(pt1, pt2));
    }

    @Test
    public void testRemoveNonExistentRequirementDoesNotThrow() {
        Assertions.assertDoesNotThrow(() -> manager.removeRequirement(pt1, pt2));
    }
}
