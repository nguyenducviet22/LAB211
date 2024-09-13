/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import employeemanagementsystem.employee.EmployeeService;
import employeemanagementsystem.employee.File;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class EmployeeManagementSystem {

    private static EmployeeService employeeService;
    private static ConsoleInputService consoleInputService;
    private static String pathFile;

    public static void main(String[] args) {
        pathFile = ("./Employee.txt");
        employeeService = new EmployeeService(pathFile);
        consoleInputService = ConsoleInputService.getInstance();
        
        employeeService.createSampleData();
        menu();
    }

    static void menu() {
        printMenu();
        boolean exit = false;
        switch (consoleInputService.getStringFromConsole()) {
            case "1":
//                employeeService.readFromFile();
                if (employeeService.isEmpty()) {
                    System.out.println("No student in the list!");
                } else {
                    System.out.println("Employee List: ");
                    employeeService.showAllEmployee();
                }
                break;
            case "2":
                employeeService.addEmployee();
//                employeeService.writeToFile();
                break;
            case "3":
                employeeService.updateEmployee();//need to sort by ID
                break;
            case "4":
                employeeService.removeEmployee();
                break;
            case "5":
                employeeService.searchEmployee();
                break;
            case "6":
                employeeService.sortEmployeeBySalary();
                break;
            case "0":
                exit = true;
                break;
            default:
                System.out.println("Invalid selection!");
        }
        if (exit == false) {
            menu();
        }
    }

    static void printMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1. Show all");
        System.out.println("2. Add");
        System.out.println("3. Update");
        System.out.println("4. Remove");
        System.out.println("5. Search");
        System.out.println("6. Sort by salary");
        System.out.println("0. Exit");
    }
}
