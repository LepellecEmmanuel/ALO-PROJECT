package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Configuration;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    private Configurator configurator;
    private Configuration configuration;

    @BeforeEach
    public void setup() {
        configurator = new ConfiguratorImpl();
        configuration = configurator.getConfiguration();
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

    }

}
