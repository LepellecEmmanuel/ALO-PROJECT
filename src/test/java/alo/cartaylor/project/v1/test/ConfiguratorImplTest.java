package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConfiguratorImplTest {

    private Configurator configurator;
    private PartTypeFactoryV2 factory;

    @BeforeEach
    public void setup() {
        factory = new PartTypeFactoryV2();
        configurator = new ConfiguratorImpl(factory);
    }

    @Test
    public void testGetCategoriesNotEmpty() {
        Set<Category> categories = configurator.getCategories();
        Assertions.assertFalse(categories.isEmpty());
    }

    @Test
    public void testGetVariantsReturnsCorrectPartTypes() {
        Category engine = factory.getCategory("Engine");
        Set<PartType> variants = configurator.getVariants(engine);
        Assertions.assertFalse(variants.isEmpty());
        for (PartType pt : variants) {
            Assertions.assertEquals(engine, pt.getCategory());
        }
    }

    @Test
    public void testGetVariantsThrowsOnNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> configurator.getVariants(null));
    }
}
