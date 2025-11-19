package alo.cartaylor.project.v1.api.impl;

import alo.cartaylor.project.v1.api.*;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Configuration implementation for V2:
 * - stores concrete Part instances by Category
 * - validates using a bound CompatibilityChecker (set by ConfiguratorImpl)
 * - printDescription produces simple HTML when configuration is complete and valid
 * - getPrice sums part property "price" (if present) and requires configuration to be valid
 */
public class ConfigurationImpl implements Configuration {

    private final Map<Category, Part> selections = new HashMap<>();

    // Bound by ConfiguratorImpl (package visible setters)
    private CompatibilityChecker compatibilityChecker = null;
    private Set<Category> availableCategories = Collections.emptySet();

    void setCompatibilityChecker(CompatibilityChecker checker) {
        this.compatibilityChecker = checker;
    }

    void setAvailableCategories(Set<Category> categories) {
        this.availableCategories = categories == null ? Collections.emptySet() : Set.copyOf(categories);
    }

    private void checkCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
    }

    private void checkPartType(PartType partType) {
        if (partType == null) throw new IllegalArgumentException("PartType cannot be null");
    }

    @Override
    public boolean isValid() {
        // no null parts
        if (selections.values().stream().anyMatch(Objects::isNull)) return false;

        // if no compatibility checker bound, consider config valid when parts are non-null
        if (compatibilityChecker == null) return true;

        Set<PartType> selectedTypes = selections.values().stream()
                .map(Part::getType)
                .collect(Collectors.toSet());

        try {
            for (Part p : selections.values()) {
                PartType t = p.getType();
                // incompatibilities
                for (PartType bad : compatibilityChecker.getIncompatibilities(t)) {
                    if (selectedTypes.contains(bad)) return false;
                }
                // requirements
                for (PartType req : compatibilityChecker.getRequirements(t)) {
                    if (!selectedTypes.contains(req)) return false;
                }
            }
        } catch (IllegalAccessException e) {
            // CompatibilityChecker declares a checked IllegalAccessException.
            // Convert to unchecked to respect Configuration API (no checked exception declared).
            throw new IllegalStateException("CompatibilityChecker access error", e);
        }

        return true;
    }

    @Override
    public boolean isCompleted() {
        if (availableCategories == null || availableCategories.isEmpty()) {
            return !selections.isEmpty();
        }
        return selections.keySet().containsAll(availableCategories);
    }

    @Override
    public Set<Part> getSelectedParts() {
        return Collections.unmodifiableSet(new HashSet<>(selections.values()));
    }

    @Override
    public Optional<Part> getSelectionForCategory(Category category) {
        checkCategory(category);
        return Optional.ofNullable(selections.get(category));
    }

    @Override
    public void selectPart(PartType chosenPart) {
        checkPartType(chosenPart);
        Part part = chosenPart.newInstance();
        if (part == null) throw new IllegalStateException("PartType.newInstance returned null");
        Category cat = part.getCategory();
        if (cat == null) throw new IllegalStateException("created Part has null category");
        selections.put(cat, part);
    }

    @Override
    public void unselectPartType(Category category) {
        checkCategory(category);
        selections.remove(category);
    }

    @Override
    public void clear() {
        selections.clear();
    }

    @Override
    public void printDescription(PrintStream stream) {
        Objects.requireNonNull(stream);
        if (!isCompleted() || !isValid()) {
            throw new IllegalStateException("Configuration must be complete and valid to print description");
        }
        stream.println("<!doctype html>");
        stream.println("<html><head><meta charset='utf-8'><title>Configuration</title></head><body>");
        stream.println("<h1>Configuration</h1>");
        stream.println("<ul>");
        for (Category cat : availableCategories) {
            Part p = selections.get(cat);
            if (p != null) {
                stream.printf("<li><strong>%s</strong>: %s", escape(cat.getName()), escape(p.getType().getName()));
                // list properties
                Set<String> propNames = p.getPropertyNames();
                if (!propNames.isEmpty()) {
                    stream.println("<ul>");
                    for (String pn : propNames) {
                        String val = p.getProperty(pn).orElse("");
                        stream.printf("<li>%s = %s</li>%n", escape(pn), escape(val));
                    }
                    stream.println("</ul>");
                } else {
                    stream.println();
                }
                stream.println("</li>");
            } else {
                stream.printf("<li><strong>%s</strong>: (none)</li>%n", escape(cat.getName()));
            }
        }
        stream.println("</ul>");
        stream.printf("<p><strong>Total price:</strong> %.2f €</p>%n", getPrice());
        stream.println("</body></html>");
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
    }

    @Override
    public double getPrice() {
        if (!isValid()) throw new IllegalStateException("Configuration must be valid to compute price");
        double sum = 0.0;
        for (Part p : selections.values()) {
            Optional<String> priceOpt = p.getProperty("price");
            if (priceOpt.isPresent()) {
                try {
                    sum += Double.parseDouble(priceOpt.get());
                } catch (NumberFormatException ignored) { /* treat as 0 */ }
            }
        }
        return sum;
    }
}