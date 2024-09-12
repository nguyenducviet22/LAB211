/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
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

    public void createNewBrand() {
        String id = getBrandIdFromConsole();
        String name = getBrandNameFromConsole();
        String country = getBrandCountryFromConsole();

        Brand b = new Brand(id, name, country);
        brandRepo.create(b);
    }

    public void showBrandList() {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
