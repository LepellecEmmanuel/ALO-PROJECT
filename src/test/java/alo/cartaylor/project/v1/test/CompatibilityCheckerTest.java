package alo.cartaylor.project.v1.test;


import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.CompatibilityChecker;

public class CompatibilityCheckerTest {

    @Test
    public void testThatGetIncompatibilitiesThrowsIllegalArgumentExceptionIfTargetIsNull() {
        PartType reference = null;
        CompatibilityChecker checker = new CompatibilityManagerImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.getIncompatibilities(reference);
        });
    }

}
