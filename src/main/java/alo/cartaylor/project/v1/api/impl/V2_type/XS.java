package alo.cartaylor.project.v1.api.impl.V2_type;

import java.util.Set;

import alo.cartaylor.project.v1.api.impl.PartImpl;

public class XS extends PartImpl {

    public enum Color {
        RED, BLUE
    }

    private Color paintcolor = Color.RED; // valeur par défaut

    public XS() {
        // On ajoute la propriété paintcolor à PartImpl
        addProperty(
                "paintcolor",
                () -> paintcolor.name(),                  // getter
                value -> paintcolor = Color.valueOf(value.toUpperCase()), // setter
                Set.of("RED", "BLUE")                     // valeurs possibles
        );
    }
}
