/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem.employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class File {

    private List<Employee> list;
    private String pathFile;

    public File(String fileName) {
        this.list = new ArrayList<>();
        this.pathFile = fileName;
    }

    public boolean writeToFile() {
        boolean status = false;
        try {
            java.io.File fo = new java.io.File(this.pathFile);
            FileOutputStream fos = new FileOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Employee e : list) {
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
                    this.list.add(e);
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
