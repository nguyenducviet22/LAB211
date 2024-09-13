/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem.employee;

import employeemanagementsystem.ConsoleInputService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class EmployeeService {

//    private static EmployeeService instance;
    private List<Employee> listEmployee;
    private ConsoleInputService consoleInputService;
    private Inputtor input;
    private String pathFile;

    public EmployeeService(String fileName) {
        listEmployee = new ArrayList<>();
        consoleInputService = ConsoleInputService.getInstance();
        input = new Inputtor();
        pathFile = fileName;
    }

//    public static EmployeeService getInstance() {
//        if (instance == null) {
//            instance = new EmployeeService();
//        }
//        return instance;
//    }
    public boolean isEmpty() {
        return this.listEmployee.size() == 0;
    }
    
    public void createSampleData(){
        listEmployee.add(new Employee("EM01", "An", "Nguyen", "0123456789", "na@gmail.com", "tp hcm", "10-10-2000", "Male", 1234, "IT"));
        listEmployee.add(new Employee("EM02", "Binh", "Nguyen", "0123456789", "na@gmail.com", "tp hcm", "10-10-2000", "Male", 1247, "SALE"));
        listEmployee.add(new Employee("EM03", "Chi", "Nguyen", "0123456789", "na@gmail.com", "tp hcm", "10-10-2000", "Female", 1236, "IT"));
        listEmployee.add(new Employee("EM04", "Duong", "Nguyen", "0123456789", "na@gmail.com", "tp hcm", "10-10-2000", "Female", 1224, "MKT"));
        listEmployee.add(new Employee("EM05", "Duc", "Nguyen", "0123456789", "na@gmail.com", "tp hcm", "10-10-2000", "Male", 1250, "ADS"));
    }

    private Employee createNewEmployee() {
        Employee newEm = new Employee();
//        String idInput;
//        while (true) {
//            idInput = input.getEmployeeIdFromConsole();
//            if (!isDuplicateId(idInput)) {
//                break;
//            }
//            System.out.print("ID has existed! Enter another ");
//        }
//        newEm.setId(idInput);
        newEm.setId(input.getEmployeeIdFromConsole());
        newEm.setFirstName(input.getEmployeeFirstNameFromConsole());
        newEm.setLastName(input.getEmployeeLastNameFromConsole());
        newEm.setPhone(input.getPhoneNumberFromConsole());
        newEm.setEmail(input.getEmailFromConsole());
        newEm.setAddress(input.getAddressFromConsole());
        newEm.setDob(input.getDOBFromConsole());
        newEm.setSex(input.getSexFromConsole());
        newEm.setSalary(input.getSalaryFromConsole());
        newEm.setAgency(input.getAgencyFromConsole());
        return newEm;
    }

    public void showAllEmployee() {
        if (listEmployee.isEmpty()) {
            System.out.println("The Employee list is empty!");
        } else {
            System.out.println("List of Employees: ");
            for (Employee em : listEmployee) {
                System.out.println(em);
            }
        }
    }

    public void addEmployee() {
        Employee emp = createNewEmployee();
        listEmployee.add(emp);
    }

    public void updateEmployee() {//cannot escape from update method yet
        System.out.println("Input employee id to update: ");
        String idEmpUpdate = input.getEmployeeIdFromConsole();
        Employee empUpdate = searchEmployeeById(idEmpUpdate);
        if (empUpdate == null) {
            System.out.println("Employee was not found! ");
            return;//show menu 
        }
        System.out.println(empUpdate);
        listEmployee.remove(empUpdate);//remove to re-add info, BUT how to retreive info has been removed

        System.out.println("Choose info to update: ");
        System.out.println("1. ID");
        System.out.println("2. First name");
        System.out.println("3. Last name");
        System.out.println("4. Phone number");
        System.out.println("5. Email");
        System.out.println("6. Address");
        System.out.println("7. Date of birth");
        System.out.println("8. Sex (Gender)");
        System.out.println("9. Salary");
        System.out.println("10. Agency");
        System.out.println("0. Cancal and Back to MAIN MENU");

        String choice = consoleInputService.getStringFromConsole();
        boolean exit = true;
        switch (choice) {
            case "1":
                empUpdate.setId(input.getEmployeeIdFromConsole());
                break;
            case "2":
                empUpdate.setFirstName(input.getEmployeeFirstNameFromConsole());
                break;
            case "3":
                empUpdate.setLastName(input.getEmployeeLastNameFromConsole());
                break;
            case "4":
                empUpdate.setPhone(input.getPhoneNumberFromConsole());
                break;
            case "5":
                empUpdate.setEmail(input.getEmailFromConsole());
                break;
            case "6":
                empUpdate.setAddress(input.getAddressFromConsole());
                break;
            case "7":
                empUpdate.setDob(input.getDOBFromConsole());
                break;
            case "8":
                empUpdate.setSex(input.getSexFromConsole());
                break;
            case "9":
                empUpdate.setSalary(input.getSalaryFromConsole());
                break;
            case "10":
                empUpdate.setAgency(input.getAgencyFromConsole());
                break;
            case "0":
                break;
            default: {
                System.out.println("Invalid selection!");
                exit = false;
            }
        }

        if (exit) {
            System.out.println("Update info successfully!");
            listEmployee.add(empUpdate);
        } else {
            updateEmployee();
        }
    }

    public void removeEmployee() {
        System.out.println("Input employee id to remove: ");
        String idEmpRemove = input.getEmployeeIdFromConsole();
        Employee empRemove = searchEmployeeById(idEmpRemove);

        if (empRemove != null) {
            System.out.println(empRemove);
            if (confirm()) {
                listEmployee.remove(empRemove);
            }
        } else {
            System.out.println("Employee was not found!");
        }
    }

    private Employee searchEmployeeById(String id) {
        for (Employee e : listEmployee) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    private boolean confirm() {
        System.out.println("Confirm to remove this employee: ");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String choice = consoleInputService.getStringFromConsole();
        switch (choice) {
            case "1":
                System.out.println("Remove employee successfully!");
                return true;
            case "2":
                System.out.println("Employee removal has been cancaled!");
                return false;
            default:
                System.out.println("Invalid selection!");
        }
        return confirm();
    }

    public void searchEmployee() {
        System.out.println("Name searching: ");
        String partOfName = consoleInputService.getStringFromConsole();
        List<Employee> listRs = searchEmployeeByName(partOfName);
        if (listRs.isEmpty()) {
            System.out.println("Not found!");
            return;
        } else {
            System.out.println("Found " + listRs.size() + " employees.");
            for (Employee l : listRs) {
                System.out.println(l);
            }
        }
    }

    private List<Employee> searchEmployeeByName(String partOfName) {
        List<Employee> listEmpResult = new ArrayList<>();
        for (Employee e : listEmployee) {
            String fullName = e.getFirstName() + " " + e.getLastName();
            if (fullName.toUpperCase().contains(partOfName.toUpperCase())) {
                listEmpResult.add(e);
            }
        }
        return listEmpResult;
    }

    public void sortEmployeeBySalary() {
        listEmployee.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());
            }
        });
        System.out.println("Salary sorted employee list: ");
        showAllEmployee();
    }

    public boolean writeToFile() {
        boolean status = false;
        try {
            java.io.File fo = new java.io.File(this.pathFile);
            FileOutputStream fos = new FileOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Employee e : listEmployee) {
                oos.writeObject(e);
            }
            oos.close();
            fos.close();
            status = true;
        } catch (FileNotFoundException ex) {
            System.out.println("File path not found!");
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Failed to write to file!");
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public void readFromFile() {
        try {
            java.io.File fi = new java.io.File(this.pathFile);
            FileInputStream fis;
            fis = new FileInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean check = true;
            while (check) {
                Employee e = (Employee) ois.readObject();
                if (e != null) {
                    this.listEmployee.add(e);
                } else {
                    check = false;
                }
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
