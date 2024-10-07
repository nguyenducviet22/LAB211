/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Menu extends Vector<String> {

    public Menu() {
        super();
    }

    public void displayMenu() {
        for (String item : this) {
            System.out.println(item);
        }
    }
}
