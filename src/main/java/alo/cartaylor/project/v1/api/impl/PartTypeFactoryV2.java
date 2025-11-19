package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.V2_type.EG100;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import alo.cartaylor.project.v1.api.impl.V2_type.*;;

public class PartTypeFactoryV2 {
    private CategoryFactory categoryFactory =  new CategoryFactory();
    private Set<PartType> partypes = new HashSet<>();

    private void generate() {
        partypes.add(new PartTypeImpl("XS", XS.class, categoryFactory.getCategory("Exterior")));
        partypes.add(new PartTypeImpl("IS", IS.class, categoryFactory.getCategory("Interior")));
    }

    public PartTypeFactoryV2() {
        generate();
    }
    public Set<PartType> getPartTypes() {
        return  Collections.unmodifiableSet(partypes);
    }

    public PartType getPartType(String name) {
        for (PartType partType : partypes) {
            if (partType.getName().equals(name)) {
                return partType;
            }
        }
        return null;
    }
}
