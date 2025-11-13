package alo.cartaylor.project.v1.api;

import java.util.Optional;
import java.util.Set;

public interface PropertyManager {
    Set<String> getPropertyNames();
    Optional<String> getProperty(String propertyName);
    void setProperty(String propertyName, String propertyValue);
    Set<String> getAvailablePropertyValues(String propertyName);
}