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
 * @author ADMIN
 */
public class BrandMenu {

    public void createBrandMenu() {
        BrandBusiness brandBus = new BrandBusiness();
        Scanner sc = new Scanner(System.in);
        String choice;

        Menu brandMenu = new Menu();
        brandMenu.add("1. Print brand list");
        brandMenu.add("2. Adding new brand");
        brandMenu.add("0. Back to main menu");

        do {
            brandMenu.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("-----------------------------------");
                    System.out.println("| ID  | Brand Name | Country       |");
                    System.out.println("-----------------------------------");
                    brandBus.showBrandList();
                    break;

                case "2":
                    brandBus.createNewBrand();
                    break;

                case "0":
                    break;

                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }
}
