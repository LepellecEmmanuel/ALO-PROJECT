package alo.cartaylor.project.v1.api.impl.V2_type;

import java.util.Set;

import alo.cartaylor.project.v1.api.impl.PartImpl;
public class XC extends PartImpl {
    public enum Color { RED, BLUE }
    private Color paintcolor = Color.RED;
    public XC() {
        addProperty("paintcolor",
                () -> paintcolor.name(),
                value -> paintcolor = Color.valueOf(value.toUpperCase()),
                Set.of("RED", "BLUE"));
    }
}
