package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;

import java.util.Set;


public class TA5 extends PartImpl {
    private final String price = "12.00";
    public TA5() {
        addProperty("price",
                () -> price,
                value -> { throw new IllegalArgumentException("Price"); },
                Set.of()
        );
    }
}