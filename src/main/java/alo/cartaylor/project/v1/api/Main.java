package alo.cartaylor.project.v1.api;

import alo.cartaylor.project.v1.api.impl.ConfiguratorImpl;

import java.util.Scanner;
import java.util.Set;

public class Main {
    static void addPartType(Configurator configurator, Scanner scanner) {
        Category[] category = configurator.getCategories().toArray(Category[]::new);
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
        PartType[] partTypes = configurator.getVariants(category[choice]).toArray(PartType[]::new);
        for(int j = 0; j < partTypes.length; j++) {
            System.out.println(j + ". " + partTypes[j].getName());
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
        System.out.println("Hello");
        while(true) {
           System.out.println("0. Change configuration");
           System.out.println("1. See configuration");
           System.out.println("2. See if configuration status");
           System.out.println("3. Reset configuration");
           System.out.print("choose option: ");
           int choice = scanner.nextInt();
           if(choice < 0 || choice > 3) {
               choice = scanner.nextInt();
           }
           if(choice == 0) {
               addPartType(configurator, scanner);
           }
           if(choice == 1) {
               Set<PartType> selection = configurator.getConfiguration().getSelectedParts();
               if(selection.isEmpty()) {
                   System.out.println("No parts selected.");
               }
               for(PartType partType : selection) {
                   System.out.println(partType.getName() + " selected.");
               }
           }
           if(choice == 2) {
               System.out.println("Configuration completed:" + configurator.getConfiguration().isCompleted());
               System.out.println("Configuration valid:" + configurator.getConfiguration().isValid());
           }
           if(choice == 3) {
               configurator.getConfiguration().clear();
               System.out.println("Configuration cleared.");
           }
        }
        //scanner.close();
    }
}
