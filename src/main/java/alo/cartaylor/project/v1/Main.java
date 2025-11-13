package alo.cartaylor.project.v1;

import alo.cartaylor.project.v1.api.*;
import alo.cartaylor.project.v1.api.impl.*;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Création de la configuration et du gestionnaire de compatibilité
        Configuration configuration = new ConfigurationImpl();
        CompatibilityManagerImpl compatibilityManager = new CompatibilityManagerImpl();

        // Création du configurateur
        Configurator configurator = new ConfiguratorImpl(configuration, compatibilityManager);

        // 1. Afficher toutes les catégories
        System.out.println("Catégories disponibles :");
        for (Category category : configurator.getCategories()) {
            System.out.println("- " + category.getName());
        }

        // 2. Sélectionner un variant pour chaque catégorie
        System.out.println("\nSélection des variantes pour chaque catégorie :");
        for (Category category : configurator.getCategories()) {
            Set<PartType> variants = configurator.getVariants(category);
            if (!variants.isEmpty()) {
                PartType chosen = variants.iterator().next();
                configuration.selectPart(chosen);
                System.out.println("Sélectionné : " + chosen.getName() + " pour " + category.getName());
            }
        }

        // 3. Vérifier si la configuration est valide
        System.out.println("\nLa configuration est-elle valide ? " + configuration.isValid());

        // 4. Supprimer une pièce (par exemple la première catégorie)
        Category firstCategory = configurator.getCategories().iterator().next();
        configuration.unselectPartType(firstCategory);
        System.out.println("Après suppression de la catégorie '" + firstCategory.getName() + "', valide ? " + configuration.isValid());

        // 5. Admin : ajouter une incompatibilité et une dépendance
        // 5. Admin : ajouter une incompatibilité et une dépendance
        // On prend deux variantes d'Engine et Transmission pour l'exemple
        Category engineCategory = configurator.getCategories().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Engine"))
                .findFirst()
                .orElse(null);
        Category transmissionCategory = configurator.getCategories().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Transmission"))
                .findFirst()
                .orElse(null);

        if (engineCategory != null && transmissionCategory != null) {
            Set<PartType> engineVariants = configurator.getVariants(engineCategory);
            Set<PartType> transmissionVariants = configurator.getVariants(transmissionCategory);

            if (!engineVariants.isEmpty() && !transmissionVariants.isEmpty()) {
                PartType engine1 = engineVariants.iterator().next();
                PartType transmission1 = transmissionVariants.iterator().next();

                compatibilityManager.addIncompatibilities(engine1, Set.of(transmission1));
                compatibilityManager.addRequirements(engine1, Set.of(transmission1));

                System.out.println("\nAdmin : incompatibilité et dépendance ajoutées entre " +
                        engine1.getName() + " et " + transmission1.getName());

                System.out.println("Incompatibles avec " + engine1.getName() + " : " +
                        compatibilityManager.getIncompatibilities(engine1));
                System.out.println("Requis avec " + engine1.getName() + " : " +
                        compatibilityManager.getRequirements(engine1));
            } else {
                System.out.println("Aucune variante trouvée pour Engine ou Transmission.");
            }
        } else {
            System.out.println("Catégorie Engine ou Transmission introuvable.");
        }
    }
}