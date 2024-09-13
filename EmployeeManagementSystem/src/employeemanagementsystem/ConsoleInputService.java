/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class ConsoleInputService {

    private static ConsoleInputService instance;
    private Scanner sc;

    private ConsoleInputService() {
        sc = new Scanner(System.in);
    }

    public static ConsoleInputService getInstance() {
        if (instance == null) {
            instance = new ConsoleInputService();
        }
        return instance;
    }

    public String getStringFromConsole() {
        return sc.nextLine();
    }
    
    public boolean checkStringWithPattern(String regexPattern, String value){
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
