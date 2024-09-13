package util;

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
public class InputValidation {

    private static InputValidation iv = new InputValidation();
    private static final String EMPLOYEE_NAME_FORMAT = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private final String PHONE_FORMAT = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    public static boolean isValid(String data, String pattern) {
        boolean rs = false;
        rs = data.matches(pattern);
        return rs;
    }

    public static String inputString(String mess) {
        String rs = "";
        Scanner sc = new Scanner(System.in);
        System.out.print(mess);
        rs = sc.nextLine();
        return rs;
    }

    public static int inputInt(String mess) {
        int rs = 0;
        String temp = inputString(mess);
        rs = Integer.parseInt(temp);
        return rs;
    }

    public static boolean inputGender(String mess) {
        boolean rs = false;
        int temp = inputInt(mess);
        rs = temp == 1;
        return rs;
    }
    
    public static String inputFullName(String mess) throws Exception{
        String fullName = inputString(mess);
        if (!iv.isValid(fullName, iv.EMPLOYEE_NAME_FORMAT)){
            throw new Exception("Invalid full name!");
        }
        return fullName;
    }

    public static String inputPhoneNumber(String mess) throws Exception {
        String phoneNumber = inputString(mess);
        if (!iv.isValid(phoneNumber, iv.PHONE_FORMAT)) {
            throw new Exception("Invalid phone number!");
        }
        return phoneNumber;
    }
}
