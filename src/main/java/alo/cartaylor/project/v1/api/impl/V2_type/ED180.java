package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;

import java.util.Set;


public class ED180 extends PartImpl {
    private final String price = "25.25";
    public ED180() {
        addProperty("price",
                () -> price,
                value -> { throw new IllegalArgumentException("Price"); },
                Set.of()
                );
    }
}