/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem.employee;

import employeemanagementsystem.ConsoleInputService;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class Inputtor {

    private ConsoleInputService consoleInputService;
    private Validation validation;

    public Inputtor() {
        consoleInputService = ConsoleInputService.getInstance();
        validation = new Validation();
    }

    public String getEmployeeIdFromConsole() {
        System.out.print("ID: ");
        String idInput = consoleInputService.getStringFromConsole();//let users input value
        if (validation.isValidEmployeeIdFormat(idInput)) {
            return idInput;//valid input
        }
        System.out.print("Inlavid ID, enter again: ");
        return getEmployeeIdFromConsole();//invalid input, re-input
    }

//    public boolean isDuplicateId(String id) {
//        for (Employee e : listEmployee) {
//            if (e.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public String getEmployeeFirstNameFromConsole() {
        System.out.print("First Name: ");
        String fNameInput = consoleInputService.getStringFromConsole().trim();
        if (validation.isValidEmployeeNameFormat(fNameInput)) {
            return fNameInput;
        }
        System.out.print("Inlavid First Name, enter again: ");
        return getEmployeeFirstNameFromConsole();
    }

    public String getEmployeeLastNameFromConsole() {
        System.out.print("Last Name: ");
        String lNameInput = consoleInputService.getStringFromConsole().trim();
        if (validation.isValidEmployeeNameFormat(lNameInput)) {
            return lNameInput;
        }
        System.out.print("Inlavid Last Name, enter again: ");
        return getEmployeeLastNameFromConsole();
    }

    public String getPhoneNumberFromConsole() {
        System.out.print("Phone Number: ");
        String phoneNumInput = consoleInputService.getStringFromConsole();
        if (validation.isValidPhoneNumberFormat(phoneNumInput)) {
            return phoneNumInput;
        }
        System.out.print("Inlavid Phone Number, enter again: ");
        return getPhoneNumberFromConsole();
    }

    public String getEmailFromConsole() {
        System.out.print("Email: ");
        String emailInput = consoleInputService.getStringFromConsole();
        if (validation.isValidEmailFormat(emailInput)) {
            return emailInput;
        }
        System.out.print("Inlavid Email, enter again: ");
        return getEmailFromConsole();
    }

    public String getAddressFromConsole() {
        System.out.print("Address: ");
        String addressInput = consoleInputService.getStringFromConsole();
        if (validation.isValidAddressFormat(addressInput)) {
            return addressInput;
        }
        System.out.print("Inlavid Address, enter again: ");
        return getAddressFromConsole();
    }

    public String getDOBFromConsole() {
        System.out.print("Date of birth: ");
        String dobInput = consoleInputService.getStringFromConsole();
        if (validation.isValidDate(Employee.DATE_OF_BIRTH, dobInput)) {
            return dobInput;
        }
        System.out.print("Inlavid Date of birth, enter again: ");
        return getDOBFromConsole();
    }

    public String getSexFromConsole() {
        System.out.println("Sex (Gender): ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");

        String choice = consoleInputService.getStringFromConsole();
        switch (choice) {
            case "1":
                return "Male";
            case "2":
                return "Female";
            case "3":
                return "Other";
            default:
                System.out.println("Invalid selection!");
        }
        return getSexFromConsole();
    }

    public double getSalaryFromConsole() {
        System.out.print("Salary: ");
        String salaryInput = consoleInputService.getStringFromConsole();
        if (validation.isValidSalary(salaryInput)) {
            return Double.parseDouble(salaryInput);
        }
        System.out.print("Inlavid Salary, enter again: ");
        return getSalaryFromConsole();
    }

    public String getAgencyFromConsole() {
        System.out.print("Agency: ");
        String agencyInput = consoleInputService.getStringFromConsole();
        if (validation.isValidAgencyFormat(agencyInput)) {
            return agencyInput;
        }
        System.out.print("Inlavid Agency, enter again: ");
        return getAgencyFromConsole();
    }
}
