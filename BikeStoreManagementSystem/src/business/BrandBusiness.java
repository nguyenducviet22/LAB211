/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
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
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    private String brandIdRegex = "B\\d{3}";
    private String brandNameRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private String brandCountryRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";

    public BrandBusiness() {
        brandRepo = new BrandRepository();
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

    public boolean isValidBrandNameFormat(String value) {
        return checkStringWithPattern(brandNameRegex, value);
    }

    public boolean isValidBrandCountryFormat(String value) {
        return checkStringWithPattern(brandCountryRegex, value);
    }

    public String getBrandIdFromConsole() {
        System.out.print("Enter Brand ID: ");
        String id = sc.nextLine();
        if (isValidBrandIdFormat(id)) {
            return id;
        }
        System.out.println("Invalid Brand ID! Try again! Eg: B000");
        return getBrandIdFromConsole();
    }

    public String getBrandNameFromConsole() {
        System.out.print("Enter Brand Name: ");
        String name = sc.nextLine();
        if (isValidBrandNameFormat(name)) {
            return name;
        }
        System.out.println("Invalid Brand Name! Try again! Eg: Nike");
        return getBrandNameFromConsole();
    }

    public String getBrandCountryFromConsole() {
        System.out.print("Enter Brand Country: ");
        String country = sc.nextLine();
        if (isValidBrandCountryFormat(country)) {
            return country;
        }
        System.out.println("Invalid Brand Country! Try again! Eg: Vietnam");
        return getBrandCountryFromConsole();
    }

    public boolean isExistedId(String id) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
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
        System.out.println("---------------------------------------");
    }

    public void showFootTable() {
        System.out.println("---------------------------------------");
    }

}
