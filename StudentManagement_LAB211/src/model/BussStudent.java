/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class BussStudent {

    private List<Student> list;
    private String pathFile;

    public BussStudent(String fileName) {
        this.list = new ArrayList<>();
        this.pathFile = fileName;
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

    public void displayAll() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("|     ID     |         Full Name         | Gender | Date of Birth | Phone Number|");
        System.out.println("---------------------------------------------------------------------------------");
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void sortByName() {
        Collections.sort(this.list);
        displayAll();
    }

    public boolean writeToFile() {
        boolean status = false;
        try {
            File fo = new File(this.pathFile);
            FileOutputStream fos = new FileOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Student s : list) {
                oos.writeObject(s);
            }
            oos.close();
            fos.close();
            status = true;
        } catch (FileNotFoundException ex) {
            System.out.println("File path not found!");
            Logger.getLogger(BussStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Failed to write to file!");
            Logger.getLogger(BussStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public void readFromFile() {
        try {
            File fi = new File(this.pathFile);
            FileInputStream fis;
            fis = new FileInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean check = true;
            while (check) {
                Student s = (Student) ois.readObject();
                if (s != null) {
                    this.list.add(s);
                } else {
                    check = false;
                }
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            Logger.getLogger(BussStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BussStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BussStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
