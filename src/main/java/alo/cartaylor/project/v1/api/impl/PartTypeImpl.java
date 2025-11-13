package alo.cartaylor.project.v1.api.impl;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import alo.cartaylor.project.v1.api.Category;
import alo.cartaylor.project.v1.api.Part;
import alo.cartaylor.project.v1.api.PartType;

/**
 * PartType implementation supporting V1 usage (name + category)
 * and V2 usage (ability to create Part instances).
 *
 * - keep compatibility with existing factories that call new PartTypeImpl(name, category)
 * - allow explicit classRef constructor for V2
 */
public class PartTypeImpl implements PartType {
    private final String name;
    private final Category category;
    private final Class<? extends PartImpl> classRef;

    // V1-compatible constructor: defaults to PartImpl.class as concrete part implementation
    public PartTypeImpl(String name, Category category) {
        this(name, PartImpl.class, category);
    }

    // V2 constructor: explicit part implementation class
    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("PartType name cannot be null or empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("PartType category cannot be null");
        }
        if (classRef == null) {
            throw new IllegalArgumentException("classRef cannot be null");
        }
        this.name = name.trim();
        this.category = category;
        this.classRef = classRef;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * Instantiate a concrete Part from the stored classRef.
     * The created instance will have its type set to this PartTypeImpl.
     */
    @Override
    public Part newInstance() {
        try {
            Constructor<? extends PartImpl> constructor = classRef.getConstructor();
            PartImpl instance = constructor.newInstance();
            instance.setType(this); // set the type on the created part
            return instance;
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed for " + classRef, e);
            throw new IllegalStateException("failed to instantiate part for type " + name, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartTypeImpl)) return false;
        PartTypeImpl that = (PartTypeImpl) o;
        return name.equals(that.name) && category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public String toString() {
        return name + " (" + category.getName() + ")";
    }
}