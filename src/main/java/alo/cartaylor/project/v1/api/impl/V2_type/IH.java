package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;
import java.util.Set;

public class IH extends PartImpl {
    private String upholstery = "standard";
    public IH() {
        addProperty("upholstery",
                () -> upholstery,
                value -> upholstery = value,
                Set.of("standard", "leather"));
    }
}
