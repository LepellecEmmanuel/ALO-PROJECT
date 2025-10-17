package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.CompatibilityManager;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompatibilityManagerTest {
    CompatibilityManager manager;

    @BeforeEach
    public void setUp() {
        manager = new CompatibilityManagerImpl();
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
}
