/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private final String ONLY_ALPHABET_AND_NUMBER_FORMAT = "[a-zA-Z0-9]+";
    private final String ONLY_NUMBER_FORMAT = "[0-9]+";
    private final String EMPLOYEE_NAME_FORMAT = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private final String PHONE_FORMAT = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    private final String EMAIL_FORMAT = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String ADDRESS_FORMAT = "^[#.0-9a-zA-Z\\s,-]+$";

    private ConsoleInputService() {
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
    
    public boolean isContainOnlyAlphabetAndNumber(String value){
        return checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }
    
    public boolean isContainOnlyNumber(String value){
        return checkStringWithPattern(ONLY_NUMBER_FORMAT, value);
    }
    
    public boolean isValidEmployeeIdFormat(String value){
        return checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }
    
    public boolean isValidEmployeeNameFormat(String value){
        return checkStringWithPattern(EMPLOYEE_NAME_FORMAT, value);
    }
    
    public boolean isValidPhoneNumberFormat(String value){
        return checkStringWithPattern(PHONE_FORMAT, value);
    }
    
    public boolean isValidEmailFormat(String value){
        return checkStringWithPattern(EMAIL_FORMAT, value);
    }
    
    public boolean isValidAddressFormat(String value){
        return checkStringWithPattern(ADDRESS_FORMAT, value);
    }
    
    public boolean isValidAgencyFormat(String value){
        return checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }
    
    private boolean checkStringWithPattern(String regexPattern, String value){
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    
    public boolean isValidDate(String value, String dateFormat){
        Date date = null;
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            date = sdf.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean isValidSalary(String value){
        try {
            double salary = Double.parseDouble(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
