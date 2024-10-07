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
import model.Brand;
import repository.BrandRepository;

/**
 *
 * @author ADMIN
 */
public class BrandBusiness {

    private BrandRepository brandRepo;
    private String brandCodeRegex = "B\\d{3}";
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

    public boolean checkStringWithPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidBrandCode(String value) {
        return checkStringWithPattern(brandCodeRegex, value);
    }
    
    public boolean isExistedCode(String code){
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getBrandCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    public String generateBrandCode() {
        String code = "B" + rd.nextInt(900) + 100;
        if (isValidBrandCode(code)) {
            if(!isExistedCode(code)){
                return code;
            }
        }
        return generateBrandCode();
    }
    
    public String getBrandCodeFromConsole(){
        String code = ip.inputString("Enter brand code: ");
        if(isValidBrandCode(code)){
            if(isExistedCode(code)){
                return code;
            }
        }
        System.out.println("Not found brand code!");
        return getBrandCodeFromConsole();
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

    public String getBrandCountryFromConsole() {
        String country = ip.inputString("Enter brand country: ");
            return country;
    }

    public void addNewBrand() {
        String code = generateBrandCode();
        String name = getBrandNameFromConsole();
        String country = getBrandCountryFromConsole();

        Brand b = new Brand(code, name, country);
        brandRepo.create(b);
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
        System.out.println("------------------------------------");
        System.out.println("| Code | Brand Name | Country      |");
        System.out.println("------------------------------------");
    }

    public void showFootTable() {
        System.out.println("------------------------------------");
    }
}
