/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class EmployeeService {
    private static EmployeeService instance;
    private List<Employee> listEmployee;
    private ConsoleInputService consoleInputService;

    private EmployeeService() {
        listEmployee = new ArrayList<>();
        consoleInputService = ConsoleInputService.getInstance();
    }
    
    private static EmployeeService getInstance(){
        if (instance == null){
            instance = new EmployeeService();
        }
        return instance;
    }
    
//1. Add employees
//2. Update employees
//3. Remove employees
//4. Search employees
//5. Sort employees by salary
    
    private Employee createNewEmployee(){
//        Employee newEm = new Employee(inputEmployeeId(), inputEmployeeFirstName(), inputEmployeeLastName(), inputPhoneNumber(), 
//                                    inputEmail(), inputAddress(), inputDOB(), inputSex(), inputSalary(), inputAgency());
        Employee newEm = new Employee();
        newEm.setId(inputEmployeeId());
        newEm.setFirstName(inputEmployeeFirstName());
        newEm.setLastName(inputEmployeeLastName());
        newEm.setPhone(inputPhoneNumber());
        newEm.setEmail(inputEmail());
        newEm.setAddress(inputAddress());
        newEm.setDob(inputDOB());
        newEm.setSex(inputSex());
        newEm.setSalary(inputSalary());
        newEm.setAgency(inputAgency());
        return newEm;
    }
    
    private String inputEmployeeId(){
        System.out.print("ID: ");
        String idInput = consoleInputService.getStringFromConsole();//let users input value
        if (consoleInputService.isValidEmployeeIdFormat(idInput)){
            return idInput;//valid input
        }
        System.out.print("Inlavid ID, enter again: ");
        return inputEmployeeId();//invalid input, re-input
    }
    
    private String inputEmployeeFirstName(){
        System.out.print("First Name: ");
        String fNameInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidEmployeeNameFormat(fNameInput)){
            return fNameInput;
        }
        System.out.print("Inlavid First Name, enter again: ");
        return inputEmployeeFirstName();
    }
    
    private String inputEmployeeLastName(){
        System.out.print("Last Name: ");
        String lNameInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidEmployeeNameFormat(lNameInput)){
            return lNameInput;
        }
        System.out.print("Inlavid Last Name, enter again: ");
        return inputEmployeeFirstName();
    }
    
    private String inputPhoneNumber(){
        System.out.print("Phone Number: ");
        String phoneNumInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidPhoneNumberFormat(phoneNumInput)){
            return phoneNumInput;
        }
        System.out.print("Inlavid Phone Number, enter again: ");
        return inputPhoneNumber();
    }
    
    private String inputEmail(){
        System.out.print("Email: ");
        String emailInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidEmailFormat(emailInput)){
            return emailInput;
        }
        System.out.print("Inlavid Email, enter again: ");
        return inputEmail();
    }
    
    private String inputAddress(){
        System.out.print("Address: ");
        String addressInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidAddressFormat(addressInput)){
            return addressInput;
        }
        System.out.print("Inlavid Address, enter again: ");
        return inputAddress();
    }
    
    private String inputDOB(){
        System.out.print("Date of birth: ");
        String dobInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidDate(Employee.DATE_OF_BIRTH, dobInput)){
            return dobInput;
        }
        System.out.print("Inlavid Date of birth, enter again: ");
        return inputAddress();
    }
    
    private String inputSex(){
        System.out.println("Sex (Gender): ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        
        String choice = consoleInputService.getStringFromConsole();
        switch (choice){
            case "1": return "Male";
            case "2": return "Female";
            case "3": return "Other";
            default: System.out.println("Invalid selection!");
        }
        return inputSex();
    }
    
    private double inputSalary(){
        System.out.print("Salary: ");
        String salaryInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidSalary(salaryInput)){
            return Double.parseDouble(salaryInput);
        }
        System.out.print("Inlavid Salary, enter again: ");
        return inputSalary();
    }
    
    private String inputAgency(){
        System.out.print("Agency: ");
        String agencyInput = consoleInputService.getStringFromConsole();
        if (consoleInputService.isValidAgencyFormat(agencyInput)){
            return agencyInput;
        }
        System.out.print("Inlavid Agency, enter again: ");
        return inputAgency();
    }
    
    public void addEmployee(){
        
    }
    
    public void updateEmployee(){
        
    }
    
    public void removeEmployee(){
        
    }
    
    public List<Employee> serchEmployeeByName(String partOfName){
        return null;
    }
    
    public void sortEmployeeBySalary(){
        listEmployee.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());
            }
        });
    }
    
    private boolean isDuplicateId(String id){
        for (Employee e : listEmployee) {
            if(e.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    
}
