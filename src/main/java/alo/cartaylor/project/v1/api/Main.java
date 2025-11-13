package alo.cartaylor.project.v1.api;

import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;

import java.util.Scanner;
import java.util.Set;

public class Main {

    static String getConfiguration(Configurator configurator) {
        String result = "";
        Set<PartType> selection = configurator.getConfiguration().getSelectedParts();
        if(selection.isEmpty()) {
            result = "No parts selected.";
            return result;
        } else {
            result = "Selection(";
            for(PartType partType : selection) {
                result += partType.getCategory().getName() +":"+partType.getName()+":"+checkPartType(partType, configurator)+",  ";
            }
            return result += ")";
        }

    }
    static String checkPartType(PartType partType, Configurator configurator) {
        String result = "Requirements(";
        Set<PartType> requirements = configurator.getCompatibilityChecker().getRequirements(partType);
        for(PartType requirement: requirements) {
            result += requirement.getName()+",";
        }
        result += ") Incompatibilities(";
        Set<PartType> incompats = configurator.getCompatibilityChecker().getIncompatibilities(partType);
        for(PartType incompat: incompats) {
            result += incompat.getName()+",";
        }
        return result += ")";
    }
    static void addPartType(Configurator configurator, Scanner scanner) {
        Category[] category = configurator.getCategories().toArray(Category[]::new);
        System.out.println(getConfiguration(configurator));
        System.out.println("Categories:");
        int choice;
        for(int i = 0; i < category.length; i++) {
            System.out.println(i + ". " + category[i].getName());
        }
        System.out.print("Select category: ");
        choice = scanner.nextInt();
        if(choice < 0 || choice > category.length) {
            choice = scanner.nextInt();
        }
        System.out.println(category[choice].getName() + " selected.");
        System.out.println(getConfiguration(configurator));
        PartType[] partTypes = configurator.getVariants(category[choice]).toArray(PartType[]::new);
        for(int j = 0; j < partTypes.length; j++) {
            System.out.println(j + ". " + partTypes[j].getName() + "  " + checkPartType(partTypes[j], configurator));
        }
        System.out.print("Select part type: ");
        choice = scanner.nextInt();
        if(choice < 0 || choice > partTypes.length) {
            choice = scanner.nextInt();
        }
        configurator.getConfiguration().selectPart(partTypes[choice]);
        System.out.println(partTypes[choice].getName() + " selected.");
    }

    public static void main(String[] args) {
        Configurator configurator = new ConfiguratorImpl();
        Scanner scanner = new Scanner(System.in);
        while(true) {
           System.out.println("0. Change configuration");
           System.out.println("1. See configuration");
           System.out.println("2. See configuration status");
           System.out.println("3. Reset configuration");
           System.out.println("4. Validate configuration and Quit");
           System.out.print("choose option: ");
           int choice = scanner.nextInt();
           if(choice < 0 || choice > 4) {
               System.out.print("choose another option: ");
               choice = scanner.nextInt();
           }
           if(choice == 0) {
               System.out.println();
               addPartType(configurator, scanner);
               System.out.println();
           }
           if(choice == 1) {
               System.out.println();
               System.out.println(getConfiguration(configurator));
               System.out.println();
           }
           if(choice == 2) {
               System.out.println();
               System.out.println("Configuration completed:" + configurator.getConfiguration().isCompleted());
               System.out.println("Configuration valid:" + configurator.getConfiguration().isValid());
               System.out.println();
           }
           if(choice == 3) {
               System.out.println();
               configurator.getConfiguration().clear();
               System.out.println("Configuration cleared.");
               System.out.println();
           }
           if(choice == 4) {
               if(!configurator.getConfiguration().isValid() || !configurator.getConfiguration().isCompleted()) {
                   System.out.println("Cannot save configuration because it is not valid or complete.");
               }
               scanner.close();
               return;
           }
        }
    }
}
