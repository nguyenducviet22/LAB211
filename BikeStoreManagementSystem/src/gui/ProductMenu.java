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
        m.add("1. Add new product");
        m.add("2. Search product by product name");
        m.add("3. Update product");
        m.add("4. Delete product");
        m.add("5. Sort product ascending");
        m.add("6. Print list products from the file");
        m.add("0. Back to main menu");

        do {
            m.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    proBus.createNewProduct();
                    break;
                case "2":
                    proBus.searchProByName();
                    break;
                case "3":
                    proBus.updateProduct();
                    break;
                case "4":
                    proBus.deleteProduct();
                    break;
                case "5":
                    proBus.sortProduct();
                    break;
                case "6":
                    proBus.showProductList();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }
}
