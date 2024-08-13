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
    private final String ALPHABET_AND_NUMBER_FORMAT = "[a-zA-Z0-9]+";

    public ConsoleInputService() {
        sc = new Scanner(System.in);
    }
    
    public static ConsoleInputService getInstance(){
        if (instance == null){
            instance = new ConsoleInputService();
        }
        return instance;
    }
    
    public String getStringFromConsole(){
        return sc.nextLine();
    }
    
    public boolean checkStringContainOnlyAlphabetAndNumber(String value){
        Pattern pattern = Pattern.compile(ALPHABET_AND_NUMBER_FORMAT);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    
}
