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
        partypes.add(new PartTypeImpl("ED180", ED180.class, categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG100", EG100.class, categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG110", EG110.class, categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG133", EG133.class, categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG210", EG210.class, categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EH120", EH120.class, categoryFactory.getCategory("Engine")));

        partypes.add(new PartTypeImpl("TA5", TA5.class, categoryFactory.getCategory("Engine")));


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
