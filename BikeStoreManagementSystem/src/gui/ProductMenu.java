/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.ProductBusiness;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ProductMenu {

    public void createProMenu() {
        ProductBusiness proBus = new ProductBusiness();
        Scanner sc = new Scanner(System.in);
        String choice;
        Menu m = new Menu();
        m.add("1. Print product list");
        m.add("2. Adding new product");
        m.add("0. Back to main menu");

        do {
            m.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("| ID  | Product Name | Brand ID | Category ID | Model | Price |");
                    System.out.println("--------------------------------------------------------------");
                    proBus.showProductList();
                    break;
                case "2":
                    proBus.createNewProduct();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }
}
