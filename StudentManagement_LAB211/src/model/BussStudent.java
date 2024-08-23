/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class BussStudent {

    private List<Student> list;

    public BussStudent() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.list.size() == 0;
    }

    public void addStudent(Student s) {
        this.list.add(s);
    }

    public Student searchByID(String ID) {
        Student student = null;
        for (Student s : list) {
            if (s.getID().toUpperCase().equals(ID.toUpperCase())) {
                student = s;
                System.out.println(student);
                break;
            }
        }
        return student;
    }

    public boolean deleteByID(String ID) {
        boolean rs = false;
        Student student = searchByID(ID);
        if (student != null) {
            this.list.remove(student);
            rs = true;
        }
        return rs;
    }
            
    public void displayAll(){
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|     ID     |         Full Name         | Gender |Phone Number|");
        System.out.println("--------------------------------------------------------------------------");
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    
    public void sortByName(){
        Collections.sort(this.list);
        displayAll();
    }
}
