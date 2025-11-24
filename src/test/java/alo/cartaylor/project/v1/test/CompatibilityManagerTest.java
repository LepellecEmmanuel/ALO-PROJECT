package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompatibilityManagerTest {
    CompatibilityManager manager;
    private PartTypeFactoryV2 factory;

    @BeforeEach
    public void setUp() {
        manager = new CompatibilityManagerImpl();
        factory = new PartTypeFactoryV2();
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalARgumentExceptionIfReferenceIsNull() {
        PartType reference = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.getIncompatibilities(reference);
        });
    }

    @Test
    public void testThatAddIncompatibilitiesThrowsIllegalARgumentExceptionIfReferenceIsInTarget() {
        PartType reference = null;
    }

    @Test
    public void testGetIncompatibilitiesThrowsIfReferenceNull() throws IllegalAccessException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            manager.getIncompatibilities(null);
        });
    }

    @Test
    public void testThatGetRequirementsThrowsIllegalArgumentExceptionIfReferenceIsNull() throws IllegalAccessException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> manager.getRequirements(null));
    }

}
