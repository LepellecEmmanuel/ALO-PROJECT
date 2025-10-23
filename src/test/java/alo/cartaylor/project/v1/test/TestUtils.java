package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Configurator;
import alo.cartaylor.project.v1.api.PartType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestUtils {
    public static Category getCategoryByName(Configurator configurator, String categoryName) {
        return configurator.getCategories().stream().filter(category -> category.getName().equals("Engine")).findFirst()
                .orElse(null);
    }

    public static PartType getPartTypeByName(Configurator configurator, String partTypeName) {
        Set<PartType> partTypes = new HashSet<>();
        for (Category category : configurator.getCategories()) {
            partTypes.addAll(configurator.getVariants(category));
        }
        return partTypes.stream().filter(partType -> partType.getName().equals(partTypeName)).findFirst().orElse(null);
    }


}
