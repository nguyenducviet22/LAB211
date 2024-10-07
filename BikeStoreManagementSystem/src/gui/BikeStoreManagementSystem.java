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
 * @author NGUYEN DUC VIET
 */
public class BikeStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductBusiness proBus = new ProductBusiness();
        Scanner sc = new Scanner(System.in);
        int choice;
        Menu pm = new Menu();
        pm.add("1. Add new product");
        pm.add("2. Search product by product name");
        pm.add("3. Update product");
        pm.add("4. Delete product");
        pm.add("5. Save products to file");
        pm.add("6. Print list products from the file");
        pm.add("Other. Back to main menu");

        try {
            do {
                System.out.println("");
                System.out.println("--PRODUCT MAIN MENU--");
                pm.displayMenu();
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        proBus.createNewProduct();
                        break;
                    case 2:
                        proBus.searchProByName();
                        break;
                    case 3:
                        proBus.updateProduct();
                        break;
                    case 4:
                        proBus.deleteProduct();
                        break;
                    case 5:
                        proBus.writeToFile();
                        break;
                    case 6:
                        proBus.showProductList();
                        break;
                }
            } while (choice < pm.size() && choice > 0);
        } catch (Exception e) {
            return;
        }
    }
}
