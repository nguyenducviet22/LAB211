/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class Utiliti {

    Utiliti util;
    private Scanner sc;
    private final String POSITIVE_INT = "[0-9]+";

    public Utiliti() {
        sc = new Scanner(System.in);
    }

    public String inputString(String mess) {
        System.out.println(mess);
        return sc.nextLine();
    }

    public int inputInt(String mess) {
        String temp = inputString(mess);
        if (isValidInt(temp)){
            int rs = Integer.parseInt(temp);//wether should declare int rs in if statement or not!!!
            return rs;
        }
        return inputInt(mess);
    }

    public boolean isValidPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidInt(String value) {
        return isValidPattern(POSITIVE_INT, value);
    }
}
