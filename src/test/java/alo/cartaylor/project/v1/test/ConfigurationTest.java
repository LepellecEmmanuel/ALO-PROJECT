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
        configurator = new ConfiguratorImpl();
        configuration = configurator.getConfiguration();
        compatibilityChecker = configurator.getCompatibilityChecker();
        factory = new PartTypeFactoryV2();
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
    public void testThatSelectingPartUpdatesSelectedParts() {
        configuration.selectPart(factory.getPartType("EG100"));
        Assertions.assertTrue(configuration.getSelectedParts().stream().anyMatch(part -> Objects.equals(part.getName(), "EG100")));
        Assertions.assertTrue(configuration.getSelectionForCategory(factory.getCategory("Engine")).stream().anyMatch(category -> Objects.equals(category.getName(), "Engine")));
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
}
