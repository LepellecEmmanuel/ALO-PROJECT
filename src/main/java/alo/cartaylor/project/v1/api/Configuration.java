package alo.cartaylor.project.v1.api;

import java.util.Set;

public interface Configuration {
    boolean isValid();
    boolean isCompleted();
    Set<PartType> getSelectedParts();
    void selectPart(PartType chosenPart);
    PartType getSelectionForCategory(Category category);
    void unselectPartType(Category category);
    void clear();
}
