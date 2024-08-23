/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement_lab211;

import java.util.Date;
import java.util.Scanner;
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
        BussStudent list = new BussStudent();
        String choice;
        do {
            choice = menu();
            switch (choice) {
                case "1":
                    Student stuAn = new Student("SE123456", "Nguyen Van An", true, new Date(2004, 9, 2), "0123456789");
                    list.addStudent(stuAn);
                    Student stuBinh = new Student("SE123457", "Hoang Thai Binh", true, new Date(2004, 10, 12), "0987654321");
                    list.addStudent(stuBinh);
                    Student stuChi = new Student("SE123458", "Pham Thanh Chi", false, new Date(2004, 2, 2), "0246813579");
                    list.addStudent(stuChi);
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
                    list.searchByID("SE123456");
                    break;
                case "4":
                    list.deleteByID("SE123456");
                    break;
                case "5":
                    System.out.println("Sorted By Name List: ");
                    list.sortByName();
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
                + "0. Exit\n"
                + "Enter your choice!";
        String rs = lib.InputScanner.inputString(mess);
        return rs;
    }
}
