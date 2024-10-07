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

    public String inputString(String mes) {
        System.out.print(mes);
        String str = sc.nextLine().trim();
        return str;
    }

    public int inputInt(String mes) {
        String temp = inputString(mes);
        int rs = Integer.parseInt(temp);
        return rs;
    }

    public double inputDouble(String mes) {
        String temp = inputString(mes);
        double rs = Double.parseDouble(temp);
        return rs;
    }
}
