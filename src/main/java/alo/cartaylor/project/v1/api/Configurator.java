package alo.cartaylor.project.v1.api;

import java.util.Set;

public interface Configurator {
    Set<Category> getCategories();
    Set<PartType> getVariants(Category category);
    Configuration getConfiguration();
    CompatibilityChecker getCompatibilityChecker();
}
