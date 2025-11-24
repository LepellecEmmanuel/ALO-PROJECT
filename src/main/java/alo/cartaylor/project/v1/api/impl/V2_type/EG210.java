package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;

import java.util.Set;


public class EG210 extends PartImpl {
    private final String price = "53.00";
    public EG210() {
        addProperty("price",
                () -> price,
                value -> { throw new IllegalArgumentException("Price"); },
                Set.of()
        );
    }
}