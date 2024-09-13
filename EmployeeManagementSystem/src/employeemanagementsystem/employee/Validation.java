/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem.employee;

import employeemanagementsystem.ConsoleInputService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class Validation {

    private ConsoleInputService consoleInputService;
    private final String ONLY_ALPHABET_AND_NUMBER_FORMAT = "[a-zA-Z0-9]+";
    private final String ONLY_NUMBER_FORMAT = "[0-9]+";
    private final String EMPLOYEE_NAME_FORMAT = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private final String PHONE_FORMAT = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    private final String EMAIL_FORMAT = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String ADDRESS_FORMAT = "^[ #.0-9a-zA-Z\\s,-]+$";

    public Validation() {
        consoleInputService = ConsoleInputService.getInstance();
    }

    public boolean isContainOnlyAlphabetAndNumber(String value) {
        return consoleInputService.checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }

    public boolean isContainOnlyNumber(String value) {
        return consoleInputService.checkStringWithPattern(ONLY_NUMBER_FORMAT, value);
    }

    public boolean isValidEmployeeIdFormat(String value) {
        return consoleInputService.checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }

    public boolean isValidEmployeeNameFormat(String value) {
        return consoleInputService.checkStringWithPattern(EMPLOYEE_NAME_FORMAT, value);
    }

    public boolean isValidPhoneNumberFormat(String value) {
        return consoleInputService.checkStringWithPattern(PHONE_FORMAT, value);
    }

    public boolean isValidEmailFormat(String value) {
        return consoleInputService.checkStringWithPattern(EMAIL_FORMAT, value);
    }

    public boolean isValidAddressFormat(String value) {
        return consoleInputService.checkStringWithPattern(ADDRESS_FORMAT, value);
    }

    public boolean isValidAgencyFormat(String value) {
        return consoleInputService.checkStringWithPattern(ONLY_ALPHABET_AND_NUMBER_FORMAT, value);
    }

    public boolean isValidDate(String dateFormat, String value) {
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

    public boolean isValidSalary(String value) {
        try {
            double salary = Double.parseDouble(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
