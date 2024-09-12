/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Menu extends Vector<String>{

    public Menu() {
        super();
    }
    
    public void displayMenu(){
        for (String item : this) {
            System.out.println(item);
        }
    }
}
