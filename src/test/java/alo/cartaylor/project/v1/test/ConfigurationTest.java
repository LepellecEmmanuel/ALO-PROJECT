package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.CompatibilityChecker;
import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.impl.CompatibilityManagerImpl;
import alo.cartaylor.project.v1.api.impl.ConfigurationImpl;
import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ConfigurationTest {
    private Configurator configurator;
    private Configuration configuration;
    private PartTypeFactoryV2 factory;
    private CompatibilityChecker compatibilityChecker;

    @BeforeEach
    public void setup() {
        factory = new PartTypeFactoryV2();
        configurator = new ConfiguratorImpl(factory);
        configuration = configurator.getConfiguration();
        compatibilityChecker = configurator.getCompatibilityChecker();
    }

    @Test
    public void testThatConfigurationInitializationSucceeds() {
        Assertions.assertNotNull(configuration);
        Assertions.assertEquals(0, configuration.getSelectedParts().size());
    }

    @Test
    public void testThatNewConfigurationIsValid() {
        Assertions.assertTrue(configuration.isValid());
    }

    @Test
    public void testThatNewConfigurationIsNotCompleted() {
        Assertions.assertFalse(configuration.isCompleted());
    }

    @Test
    public void testThatCompletedConfigurationIsComplete() {
        configuration.selectPart(factory.getPartType("EG100"));
        configuration.selectPart(factory.getPartType("IN"));
        configuration.selectPart(factory.getPartType("TM5"));
        configuration.selectPart(factory.getPartType("XC"));
        Assertions.assertTrue(configuration.isCompleted());
    }

    @Test
    public void testThatSelectingPartUpdatesSelectedParts() {
        configuration.selectPart(factory.getPartType("EG100"));
        Assertions.assertTrue(configuration.getSelectedParts().stream().anyMatch(part -> Objects.equals(part.getType().getName(), "EG100")));
        Assertions.assertEquals("EG100",
                configuration.getSelectionForCategory(factory.getCategory("Engine")).get().getType().getName());

    }

    @Test
    public void testThatIsValidReturnsFalseForIncompatiblePartTypes() {
        configuration.selectPart(factory.getPartType("EG210"));
        configuration.selectPart(factory.getPartType("XS"));
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    public void testThatIsValidReturnsFalseForMissingRequirements() {
        configuration.selectPart(factory.getPartType("IS"));
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    public void testThatSelectPartReplacesPreviousPartForCategory() {
        configuration.selectPart(factory.getPartType("EG100"));
        configuration.selectPart(factory.getPartType("EG110"));
        Assertions.assertEquals("EG110",
                configuration.getSelectionForCategory(factory.getCategory("Engine")).get().getType().getName());
    }

    @Test
    public void testThatUnselectPartRemovesPart() {
        configuration.selectPart(factory.getPartType("EG100"));
        configuration.unselectPartType(factory.getCategory("Engine"));
        Assertions.assertTrue(configuration.getSelectedParts().isEmpty());
    }

    @Test
    public void testThatClearRemovesAllParts() {
        configuration.selectPart(factory.getPartType("EG100"));
        configuration.selectPart(factory.getPartType("IN"));
        configuration.clear();
        Assertions.assertTrue(configuration.getSelectedParts().isEmpty());
    }

    @Test
    public void testValidConfigurationWithRequirementsSatisfied() {
        configuration.selectPart(factory.getPartType("XS"));
        configuration.selectPart(factory.getPartType("IS"));
        Assertions.assertTrue(configuration.isValid());
    }

    @Test
    public void testInvalidComplexCombination() {
        configuration.selectPart(factory.getPartType("EG100"));
        configuration.selectPart(factory.getPartType("TA5")); // incompatible with EG100
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    public void testThatUncompletedConfigurationIsNotComplete() {
        configuration.selectPart(factory.getPartType("EG100"));
        Assertions.assertFalse(configuration.isCompleted());
    }

}
