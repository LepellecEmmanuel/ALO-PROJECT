package alo.cartaylor.project.v1.test;


import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.CompatibilityChecker;

import java.util.List;
import java.util.Set;

public class CompatibilityCheckerTest {
    private Configurator configurator;
    private CompatibilityChecker checker;

    @BeforeEach
    public void setUp() {
        configurator = new ConfiguratorImpl();
        checker = configurator.getCompatibilityChecker();
    }

    @Test
    public void testThatGetIncompatibilitiesThrowsIllegalArgumentExceptionIfReferenceIsNull() {
        PartType reference = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.getIncompatibilities(reference);
        });
    }

    @Test
    public void testThatGetRequirementsThrowsIllegalArgumentExceptionIfReferenceIsNull() {
        PartType reference = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.getRequirements(reference);
        });
    }

    @Test
    public void testThatGetIncompatibilitiesReturnAnImmutableSet() {
        PartType reference = TestUtils.getPartTypeByName(configurator, "EG100");
        Set<PartType> result = checker.getIncompatibilities(reference);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            result.add(reference);
        });
    }

    @Test
    public void testThatGetRequirementsReturnAnImmutableSet() {
        PartType reference = TestUtils.getPartTypeByName(configurator, "EG100");
        Set<PartType> result = checker.getRequirements(reference);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            result.add(reference);
        });
    }

}
