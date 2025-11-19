// ...new file...
package alo.cartaylor.project.v1.api.impl;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Part;
import alo.cartaylor.project.v1.api.PartType;


/**
 * Basic Part implementation with a simple PropertyManager.
 * Provides a default no-arg constructor (used by reflection in PartTypeImpl.newInstance()).
 */
public class PartImpl implements Part {

    // the PartType associated with this Part instance
    private PartType type;

    // small helper structure for properties
    private class Property {
        public final Supplier<String> getter;
        public final Consumer<String> setter;
        public final Set<String> possibleValues;

        Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
            this.getter = getter;
            this.setter = setter;
            this.possibleValues = possibleValues == null ? Collections.emptySet() : Set.copyOf(possibleValues);
        }
    }

    private final Map<String, Property> properties = new HashMap<>();

    // Default constructor required for reflective instantiation
    public PartImpl() {
    }

    // package-visible setter so PartTypeImpl can set the type after instantiation
    void setType(PartType type) {
        this.type = type;
    }

    @Override
    public PartType getType() {
        return type;
    }

    @Override
    public Category getCategory() {
        return type == null ? null : type.getCategory();
    }

    /**
     * Add a property with optional possible values.
     * Protected so subclasses can expose properties during construction.
     */
    protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
        Objects.requireNonNull(name, "property name cannot be null");
        properties.put(name, new Property(getter, setter, possibleValues));
    }

    @Override
    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    @Override
    public Optional<String> getProperty(String propertyName) {
        Objects.requireNonNull(propertyName);
        if (properties.containsKey(propertyName)) {
            try {
                return Optional.ofNullable(properties.get(propertyName).getter.get());
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "getter failed for property " + propertyName, e);
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public void setProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Objects.requireNonNull(propertyValue);
        if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
            try {
                properties.get(propertyName).setter.accept(propertyValue);
            } catch (Exception e) {
                Logger.getGlobal().log(Level.WARNING, "setter failed for property " + propertyName, e);
                throw new IllegalArgumentException("failed to set property: " + propertyName, e);
            }
        } else {
            throw new IllegalArgumentException("bad property name or value: " + propertyName);
        }
    }

    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
        }
        return Collections.emptySet();
    }

    @Override
    public String toString() {
        return (type == null ? "<untyped>" : type.getName()) + "@" + Integer.toHexString(System.identityHashCode(this));
    }
}
// ...new file...