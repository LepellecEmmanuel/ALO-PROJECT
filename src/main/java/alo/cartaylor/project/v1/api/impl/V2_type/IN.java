package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;
import java.util.Set;
public class IN extends PartImpl {
    private String upholstery = "standard";
    public IN() {
        addProperty("upholstery",
                () -> upholstery,
                value -> upholstery = value,
                Set.of("standard", "leather"));
    }
}