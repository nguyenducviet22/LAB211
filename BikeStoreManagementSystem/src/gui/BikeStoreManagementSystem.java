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
        BrandMenu bm = new BrandMenu();
        CategoryMenu cm = new CategoryMenu();
        ProductMenu pm = new ProductMenu();
        Scanner sc = new Scanner(System.in);
        
        Menu m = new Menu();
        m.add("1. Manage Product");
        m.add("2. Manage Brand");
        m.add("3. Manage Category");
        m.add("0. Exit!");
        String choice;
        
        do {            
            m.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextLine().trim();
            
            switch (choice) {
                case "1":
                    pm.createProMenu();
                    break;
                case "2":
                    bm.createBrandMenu();
                    break;
                case "3":
                    cm.createCateMenu();
                    break;
                case "0":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));

    }

}
