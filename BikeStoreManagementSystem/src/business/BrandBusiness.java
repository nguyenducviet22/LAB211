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
 * @author NGUYEN DUC VIET
 */
public class BrandBusiness {

    private BrandRepository brandRepo;
    Inputtor ip;
    Random rd = new Random();
    private String brandIdRegex = "B\\d{3}";

    public BrandBusiness() {
        brandRepo = new BrandRepository();
        ip = new Inputtor();
        brandRepo.readDataFromFile("Brand.txt");
    }

    public BrandBusiness(BrandRepository brandRepo) {
        this.brandRepo = brandRepo;
    }

    public BrandRepository getBrandRepo() {
        return brandRepo;
    }

    public void setBrandRepo(BrandRepository brandRepo) {
        this.brandRepo = brandRepo;
    }

    public boolean checkStringWithPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidBrandIdFormat(String value) {
        return checkStringWithPattern(brandIdRegex, value);
    }

    public boolean isExistedId(String id) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String getBrandIdFromConsole() {
        String id = ip.inputString("Enter Brand ID: ");
        if (isValidBrandIdFormat(id)) {
            if (isExistedId(id)) {
                return id;
            }
        }
        System.out.println("Invalid Brand ID! Try again!");
        return getBrandIdFromConsole();
    }

    public boolean isExistedName(String name) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String getBrandNameFromConsole() {
        String name = ip.inputString("Enter Brand Name: ").trim();
        if (name.equals("")) {
            return "";
        } else if (isExistedName(name)) {
            return name;
        }
        System.out.println("Brand name is not existed!");
        return getBrandNameFromConsole();
    }

    public String getBrandCountryFromConsole() {
        String country = ip.inputString("Enter Brand Country: ");
        return country;
    }

    public void createNewBrand() {
        String id;
        while (true) {
            id = "B" + (rd.nextInt(900) + 100);
            if (!isExistedId(id)) {
                break;
            }
        }
        String name = getBrandNameFromConsole();
        String country = getBrandCountryFromConsole();

        Brand b = new Brand(id, name, country);
        brandRepo.create(b);
        brandRepo.writeDataToFile("Brand.txt");
        showBrandList();
    }

    public void showBrandList() {
        if (brandRepo.entrySet().isEmpty()) {
            System.out.println("Brand file is empty!");
            System.out.println("");
        } else {
            showHeadTable();
            for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }

    public void showHeadTable() {
        System.out.println("---------------------------------------");
        System.out.println("| ID   | Brand Name  | Country        |");
        System.out.println("|------|-------------|----------------|");
    }

    public void showFootTable() {
        System.out.println("---------------------------------------");
    }

}
