package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.CategoryFactory;
import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class ConfiguratorTest {
    private Configurator configurator;

    @BeforeEach
    public void setup() {
        configurator = new ConfiguratorImpl();
    }

    @Test
    public void testThatConfiguratorInitializationSucceeds() {
        Assertions.assertNotNull(configurator);
    }

    @Test
    public void testThatGetCategoriesReturnsCorrectCategories() {
        Set<String> expectedCategories = Set.of("Engine", "Transmission", "Exterior", "Interior");
        Assertions.assertNotNull(configurator.getCategories());
        Assertions.assertEquals(expectedCategories ,configurator.getCategories().stream().map(Category::getName).collect(Collectors.toSet()));
    }

    @Test
    public void testThatGetVariantsReturnsCorrectVariantsForCategoryEngine() {
        Set<String> expectedVariants = Set.of("EG100", "EG133", "EG210", "ED110", "ED180", "EH120");
        Set<String> variants = configurator.getVariants(TestUtils.getCategoryByName(configurator, "Engine")).stream().map(PartType::getName).collect(Collectors.toSet());
        Assertions.assertEquals(expectedVariants, variants);
    }
}
