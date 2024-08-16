/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import employeemanagementsystem.employee.EmployeeService;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class EmployeeManagementSystem {

    private static EmployeeService employeeService;
    private static ConsoleInputService consoleInputService;
    
    public static void main(String[] args) {
        employeeService = EmployeeService.getInstance();
        consoleInputService = ConsoleInputService.getInstance();
    }
    
    static void printMenu(){
        System.out.println("MAIN MENU");
        System.out.println("0. Show all");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Remove");
        System.out.println("4. Search");
        System.out.println("5. Sort by salary");
        System.out.println("6. Exit");
    }
}
