/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Category;
import repository.CategoryRepository;

/**
 *
 * @author ADMIN
 */
public class CategoryBusiness {
    private CategoryRepository cateRepo;
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    private String cateIdRegex = "C\\d{3}";
    private String cateNameRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";

    public CategoryBusiness() {
        cateRepo = new CategoryRepository();
        cateRepo.readDataFromFile("Category.txt");
    }

    public CategoryBusiness(CategoryRepository cateRepo) {
        this.cateRepo = cateRepo;
    }

    public CategoryRepository getCateRepo() {
        return cateRepo;
    }

    public void setCateRepo(CategoryRepository cateRepo) {
        this.cateRepo = cateRepo;
    }
    
    public boolean checkStringWithPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidCateIdFormat(String value) {
        return checkStringWithPattern(cateIdRegex, value);
    }

    public boolean isValidCateNameFormat(String value) {
        return checkStringWithPattern(cateNameRegex, value);
    }
    
    public String getCateIdFromConsole() {
        System.out.print("Enter Category ID: ");
        String id = sc.nextLine();
        if (isValidCateIdFormat(id)) {
            return id;
        }
        System.out.println("Invalid Category ID! Try again!");
        return getCateIdFromConsole();
    }

    public String getCateNameFromConsole() {
        System.out.print("Enter Category Name: ");
        String name = sc.nextLine();
        if (isValidCateNameFormat(name)) {
            return name;
        }
        System.out.println("Invalid Category Name! Try again!");
        return getCateNameFromConsole();
    }
    
    public boolean isExistedId(String id) {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public void createNewCate(){
        String id;
        while (true) {
            id = "C" + (rd.nextInt(900) + 100);;
            if (!isExistedId(id)) {
                break;
            }
        }
        String name = getCateNameFromConsole();
        
        Category c = new Category(id, name);
        cateRepo.create(c);
        cateRepo.writeDataToFile("Category.txt");
    }
    
    public void showBrandList() {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    
}
