package alo.cartaylor.project.v1.api;

import java.util.Optional;

public interface Part extends PropertyManager {
    default String getName() {
        return this.getClass().getTypeName();
    }
    Category getCategory();
    PartType getType();
}