package alo.cartaylor.project.v1.api;

public interface PartType {
    String getName();
    Category getCategory();

     /**
     * V2: create a new Part instance for this type.
     * Implementations may throw runtime exceptions on failure.
     */
    Part newInstance();
}
