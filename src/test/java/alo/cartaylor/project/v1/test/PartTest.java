package alo.cartaylor.project.v1.test;

import alo.cartaylor.project.v1.api.Part;
import alo.cartaylor.project.v1.api.PartType;
import alo.cartaylor.project.v1.api.impl.PartTypeFactoryV2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PartTest {
    private PartTypeFactoryV2 factory;

    @BeforeEach
    public void setup() {
        factory = new PartTypeFactoryV2();
    }

    @Test
    public void testThatPartCreationSucceeds() {
        Part xc = factory.getPartType("XC").newInstance();
        Assertions.assertEquals("XC", xc.getType().getName());
        Assertions.assertEquals(xc.getAvailablePropertyValues("paintcolor"), Set.of("RED", "BLUE"));
        Assertions.assertEquals(xc.getProperty("paintcolor").get(), "RED");
    }

    @Test
    public void testThatPartPropertyCannotBeAssignedWithInvalidValue() {
        Part xc = factory.getPartType("XC").newInstance();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            xc.setProperty("paintcolor", "INVALID");
        });
    }

    @Test
    public void testThatPartCannotAssignedToUnknownProperty() {
        Part xc = factory.getPartType("XC").newInstance();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            xc.setProperty("outch!", "RED");
        });
    }

    //@Test
    //public void testThatSetValueUpdatePartType
}
