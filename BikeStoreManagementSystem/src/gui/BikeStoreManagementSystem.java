/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.BrandBusiness;
import java.util.Scanner;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class BikeStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BrandBusiness brandBus = new BrandBusiness();
        Scanner sc = new Scanner(System.in);
        int choice;

        Menu brandMenu = new Menu();
        brandMenu.add("1. Print brand list");
        brandMenu.add("2. Adding new brand");
        brandMenu.add("0. Exit program");

        do {
            brandMenu.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("| ID | Brand Name | Country       |");
                    System.out.println("-----------------------------------");
                    brandBus.showBrandList();
                    break;

                case 2:
                    brandBus.createNewBrand();
                    break;

                case 0:
                    System.out.println("Bye bye!");
                    break;
                    
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice < brandMenu.size() && choice > 0);
    }

}
