/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.CategoryBusiness;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class CategoryMenu {

    public void createCateMenu() {
        CategoryBusiness cateBus = new CategoryBusiness();
        Scanner sc = new Scanner(System.in);
        String choice;

        Menu m = new Menu();
        m.add("1. Print category list");
        m.add("2. Add new category");
        m.add("0. Back to main menu");

        do {
            m.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    cateBus.showCateList();
                    break;
                case "2":
                    cateBus.createNewCate();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }
}
