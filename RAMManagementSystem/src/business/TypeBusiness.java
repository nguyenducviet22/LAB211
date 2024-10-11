/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Type;
import repository.TypeRepository;

/**
 *
 * @author ADMIN
 */
public class TypeBusiness {

    private TypeRepository typeRepo;
    Random rd = new Random();
    Inputtor ip;

    public TypeBusiness() {
        typeRepo = new TypeRepository();
        ip = new Inputtor();
        typeRepo.readDataFromFile("Type.txt");
    }

    public TypeBusiness(TypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }
    
    public boolean isExistedName(String name){
        for (Map.Entry<String, Type> entry : typeRepo.entrySet()) {
            if (entry.getValue().getTypeName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public String getTypeNameFromConsole() {
        String name = ip.inputString("Enter type name: ");
        if (isExistedName(name)) {
            return name;
        }
        System.out.println("Existed type name! try again");
        return getTypeNameFromConsole();
    }

    public void showTypeList() {
        if (typeRepo.entrySet().isEmpty()) {
            System.out.println("Type list is empty!");
        } else {
            showHeadTable();
            for (Map.Entry<String, Type> entry : typeRepo.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }
    
    public void showHeadTable() {
        System.out.println("--------------");
        System.out.println("| Type Name  |");
        System.out.println("|------------|");
    }

    public void showFootTable() {
        System.out.println("--------------");
    }
}
