/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement_lab211;

import lib.InputScanner;
import model.BussStudent;
import model.Student;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class StudentManagement_LAB211 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String pathFile = "./StudentData.dat";
        BussStudent list = new BussStudent(pathFile);
        String choice;
        do {
            choice = menu();
            switch (choice) {
                case "1":
                    Student student = createNewStudent();
                    list.addStudent(student);
                    break;
                case "2":
                    if (list.isEmpty()) {
                        System.out.println("No student in the list!");
                    } else {
                        System.out.println("Student List: ");
                        list.displayAll();
                    }
                    break;
                case "3":
                    if (list.isEmpty()) {
                        System.out.println("No student in the list!");
                    } else {
                        String searchID = InputScanner.inputString("Enter ID to search: ");
                        if (list.searchByID(searchID) != null) {
                        } else {
                            System.out.println("Not found!");
                        }
                    }
                    break;
                case "4":
                    if (list.isEmpty()) {
                        System.out.println("No student in the list!");
                    } else {
                        String deleteID = InputScanner.inputString("Enter ID to delete: ");
                        if (list.deleteByID(deleteID)) {
                            System.out.println("Deleted!");
                        } else {
                            System.out.println("ID does not exist!");
                        }
                    }
                    break;
                case "5":
                    System.out.println("Sorted By Name List: ");
                    list.sortByName();
                    break;
                case "6":
                    list.readFromFile();
                    if (list.isEmpty()){
                        System.out.println("File is empty!");
                    } else {
                        list.displayAll();
                    }
                    break;
                case "7":
                    list.writeToFile();
                    System.out.println("Write to file successfully!");
                    break;
                case "0":
                    System.out.println("Bye bye!!!");
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }

    private static String menu() {
        String mess = "MAIN MENU\n"
                + "1. Add\n"
                + "2. Display All\n"
                + "3. Search\n"
                + "4. Delete\n"
                + "5. Sort By Full Name\n"
                + "6. Read from file\n"
                + "7. Write to file\n"
                + "0. Exit\n"
                + "Enter your choice: ";
        String rs = InputScanner.inputString(mess);
        return rs;
    }

    private static Student createNewStudent() {
        Student s = new Student();
        System.out.println("Enter student info: ");
        s.setID(InputScanner.inputString("Student ID: "));
        s.setFullName(InputScanner.inputString("Full Name: "));
        s.setGender(InputScanner.inputGender("Gender (1: Male - 2: Female): "));
        s.setDob(InputScanner.inputString("Date of Birth: "));
        s.setPhoneNumber(InputScanner.inputString("Phone Number: "));
        return s;
    }
}
