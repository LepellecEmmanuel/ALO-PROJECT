package alo.cartaylor.project.v1.api.impl.V2_type;
 import alo.cartaylor.project.v1.api.impl.PartImpl;
 
import java.util.Set;

public class IS extends PartImpl {

    private String upholstery = "standard"; // valeur par défaut

    public IS() {
        addProperty(
                "upholstery",
                () -> upholstery,
                value -> upholstery = value,
                Set.of("standard", "leather") // valeurs possibles
        );
    }
}
