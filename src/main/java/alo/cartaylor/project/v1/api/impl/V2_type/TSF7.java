package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;

import java.util.Set;


public class TSF7 extends PartImpl {
    private final String price = "115.25";
    public TSF7() {
        addProperty("price",
                () -> price,
                value -> { throw new IllegalArgumentException("Price"); },
                Set.of()
        );
    } }