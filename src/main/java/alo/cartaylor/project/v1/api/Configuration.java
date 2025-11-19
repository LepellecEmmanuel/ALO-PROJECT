package alo.cartaylor.project.v1.api;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Set;

public interface Configuration {
    boolean isValid();
    boolean isCompleted();

    // V2 signatures: operate on Part instances
    Set<Part> getSelectedParts();
    Optional<Part> getSelectionForCategory(Category category);

    // selection API (creates Part instances via PartType.newInstance)
    void selectPart(PartType chosenPart);
    void unselectPartType(Category category);
    void clear();

    // US #6: print an HTML description of the configuration if it is complete and valid
    void printDescription(PrintStream stream);

    // US #7: get total price in euros if configuration is valid (may be incomplete)
    double getPrice();
}