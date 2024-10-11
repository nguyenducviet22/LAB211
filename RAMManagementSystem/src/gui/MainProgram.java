/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Inputtor;
import business.RAMBusiness;

/**
 *
 * @author ADMIN
 */
public class MainProgram {

    public static void main(String[] args) {
        RAMBusiness rb = new RAMBusiness();
        Menu mm = new Menu();
        Inputtor ip = new Inputtor();
        
        mm.add("");
        mm.add("--MAIN MENU--");
        mm.add("1. Add an Item");
        mm.add("2. Search SubMenu");
        mm.add("3. Update Item Information");
        mm.add("4. Delete Item");
        mm.add("5. Show All Items");
        mm.add("6. Store Data to Files");
        mm.add("7. Quit Menu");
        String choice;
        do {            
            mm.displayMenu();
            choice = ip.inputString("Enter your choice: ");
            
            switch (choice){
                case "1":
                    rb.addAnItem();
                    break;
                case "2":
                    rb.searchOnSubMenu();
                    break;
                case "3":
                    rb.updateItemInfo();
                    break;
                case "4":
                    rb.deleteItem();
                    break;
                case "5":
                    rb.showAllItems();
                    break;
                case "6":
                    rb.storeDataToFile();
                    break;
                case "7":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("7"));
    }
}
