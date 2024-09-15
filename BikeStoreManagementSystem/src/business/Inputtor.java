/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Inputtor {
    Scanner sc = new Scanner(System.in);
        public String inputString(){
        return sc.nextLine().trim();
    }
}
