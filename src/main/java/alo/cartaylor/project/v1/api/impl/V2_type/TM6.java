package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;

import java.util.Set;


public class TM6 extends PartImpl {
    private final String price = "30.00";
    public TM6() {
        addProperty("price",
                () -> price,
                value -> { throw new IllegalArgumentException("Price"); },
                Set.of()
        );
    }
}