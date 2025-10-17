package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.PartType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PartTypeFactory {
    private static final Set<Category> categories = CategoryFactory.generate();

    private static Category getCategory(String name) {
        return categories.stream().filter(category -> category.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
    }

    public static Set<PartType> generate() {
        Set<PartTypeImpl> partypes = new HashSet<PartTypeImpl>();
        partypes.add(new PartTypeImpl("EG100", getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG133", getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG210", getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG110", getCategory("Engine")));
        partypes.add(new PartTypeImpl("ED180", getCategory("Engine")));
        partypes.add(new PartTypeImpl("EH120", getCategory("Engine")));
        partypes.add(new PartTypeImpl("TM5", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TM6", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TA5", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TS6", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TSF7", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TC120", getCategory("Transmission")));
        partypes.add(new PartTypeImpl("XC", getCategory("Exterior")));
        partypes.add(new PartTypeImpl("XM", getCategory("Exterior")));
        partypes.add(new PartTypeImpl("XS", getCategory("Exterior")));
        partypes.add(new PartTypeImpl("IN", getCategory("Interior")));
        partypes.add(new PartTypeImpl("IH", getCategory("Interior")));
        partypes.add(new PartTypeImpl("IS", getCategory("Interior")));
        return Collections.unmodifiableSet(partypes);
    }
}
