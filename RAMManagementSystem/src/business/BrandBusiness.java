/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
import java.util.Random;
import model.Brand;
import repository.BrandRepository;

/**
 *
 * @author ADMIN
 */
public class BrandBusiness {

    private BrandRepository brandRepo;
    Random rd = new Random();
    Inputtor ip;

    public BrandBusiness() {
        brandRepo = new BrandRepository();
        ip = new Inputtor();
        brandRepo.readDataFromFile("Brand.txt");
    }

    public BrandBusiness(BrandRepository brandRepo) {
        this.brandRepo = brandRepo;
    }
    
    public boolean isExistedName(String name){
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getBrandName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public String getBrandNameFromConsole() {
        String name = ip.inputString("Enter brand name: ");
        if (isExistedName(name)) {
            return name;
        }
        System.out.println("Existed brand name! try again");
        return getBrandNameFromConsole();
    }
    
    public void showBrandList(){
        if (brandRepo.entrySet().isEmpty()){
            System.out.println("Brand list is empty!");
        } else {
            showHeadTable();
            for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }
    
    public void showHeadTable() {
        System.out.println("--------------");
        System.out.println("| Brand Name |");
        System.out.println("--------------");
    }

    public void showFootTable() {
        System.out.println("--------------");
    }
}
