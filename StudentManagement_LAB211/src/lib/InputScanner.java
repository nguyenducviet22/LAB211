package lib;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NGUYEN DUC VIET
 */
public class InputScanner {

    public static String inputString(String mess) {
        String rs = "";
        Scanner sc = new Scanner(System.in);
        System.out.println(mess);
        rs = sc.nextLine();
        return rs;
    }

    public static int inputInt(String mess) {
        int rs = 0;
        String temp = inputString(mess);
        rs = Integer.parseInt(temp);
        return rs;
    }
}
