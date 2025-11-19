package alo.cartaylor.project.v1.api.impl.V2_type;

import alo.cartaylor.project.v1.api.impl.PartImpl;
import java.util.Set;
public class XM extends PartImpl {
    public enum Color { RED, BLUE }
    private Color paintcolor = Color.RED;
    public XM() {
        addProperty("paintcolor",
                () -> paintcolor.name(),
                value -> paintcolor = Color.valueOf(value.toUpperCase()),
                Set.of("RED", "BLUE"));
    }
}
