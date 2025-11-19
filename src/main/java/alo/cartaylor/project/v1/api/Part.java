package alo.cartaylor.project.v1.api;

public interface Part extends PropertyManager {
    default String getName() {
        return this.getClass().getTypeName();
    }
    Category getCategory();
    PartType getType();
}