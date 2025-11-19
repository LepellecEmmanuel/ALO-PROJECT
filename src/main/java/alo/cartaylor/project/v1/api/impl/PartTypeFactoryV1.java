package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.PartType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PartTypeFactoryV1 {
    private CategoryFactory categoryFactory =  new CategoryFactory();
    private Set<PartType> partypes = new HashSet<>();

    private void generate() {
        partypes.add(new PartTypeImpl("EG100", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG133", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG210", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EG110", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("ED180", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("EH120", categoryFactory.getCategory("Engine")));
        partypes.add(new PartTypeImpl("TM5", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TM6", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TA5", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TS6", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TSF7", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("TC120", categoryFactory.getCategory("Transmission")));
        partypes.add(new PartTypeImpl("XC", categoryFactory.getCategory("Exterior")));
        partypes.add(new PartTypeImpl("XM", categoryFactory.getCategory("Exterior")));
        partypes.add(new PartTypeImpl("XS", categoryFactory.getCategory("Exterior")));
        partypes.add(new PartTypeImpl("IN", categoryFactory.getCategory("Interior")));
        partypes.add(new PartTypeImpl("IH", categoryFactory.getCategory("Interior")));
        partypes.add(new PartTypeImpl("IS", categoryFactory.getCategory("Interior")));
    }

    public PartTypeFactoryV1() {
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
