package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Part;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PartImplTest {

    private PartTypeFactoryV2 factory;

    @BeforeEach
    public void setup() {
        factory = new PartTypeFactoryV2();
    }

    @Test
    public void testToStringReturnsTypeAndHashCode() {
        Part p = factory.getPartType("EG100").newInstance();
        String str = p.toString();
        Assertions.assertTrue(str.contains("EG100"));
        Assertions.assertTrue(str.contains("@")); // contient hashcode
    }
}
