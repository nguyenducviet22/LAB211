/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.Map;
import java.util.Random;
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
    Inputtor input;
    Random rd = new Random();
    private String cateIdRegex = "C\\d{3}";

    public CategoryBusiness() {
        cateRepo = new CategoryRepository();
        input = new Inputtor();
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

    public String getCateIdFromConsole() {
        String id = input.inputString("Enter Category ID: ");
        if (isValidCateIdFormat(id)) {
            if (isExistedId(id)) {
                return id;
            }
        }
        System.out.println("Invalid Category ID! Try again!");
        return getCateIdFromConsole();
    }

    public String getCateNameFromConsole() {
        String name = input.inputString("Enter Category Name: ");
        if (name.equals("")) {
            return "";
        } else if (isExistedName(name)) {
            return name;
        }
        System.out.println("Category name is not existed!");
        return getCateNameFromConsole();
    }

    //To check existed or duplicated name
    public boolean isExistedName(String name) {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistedId(String id) {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void createNewCate() {
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
        showCateList();
    }

    public void showCateList() {
        if (cateRepo.entrySet().isEmpty()) {
            System.out.println("Category list is empty!");
            System.out.println("");
        } else {
            showHeadTable();
            for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }

    public void showHeadTable() {
        System.out.println("-------------------------");
        System.out.println("| ID   | Category Name  |");
        System.out.println("|------|----------------|");
    }

    public void showFootTable() {
        System.out.println("-------------------------");
    }

}
