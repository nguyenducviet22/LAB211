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
import model.Product;
import repository.ProductRepository;

/**
 *
 * @author ADMIN
 */
public class ProductBusiness {

    private ProductRepository proRepo;
    private BrandBusiness brandBus;
    private CategoryBusiness cateBus;
    Scanner sc = new Scanner(System.in);
    Random rd = new Random();
    private String proIdRegex = "P\\d{3}";
    private String proNameRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private String proModelYearRegex = "\\d{4}";
    private String proListPriceRegex = "\\d+(\\.\\d{1,2})?";

    public ProductBusiness() {
        proRepo = new ProductRepository();
        brandBus = new BrandBusiness();
        cateBus = new CategoryBusiness();
        proRepo.readDataFromFile("Product.txt");
    }

    public ProductBusiness(ProductRepository proRepo) {
        this.proRepo = proRepo;
    }

    public ProductRepository getProRepo() {
        return proRepo;
    }

    public void setProRepo(ProductRepository proRepo) {
        this.proRepo = proRepo;
    }

    public boolean checkStringWithPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidProIdFormat(String value) {
        return checkStringWithPattern(proIdRegex, value);
    }

    public boolean isValidProNameFormat(String value) {
        return checkStringWithPattern(proNameRegex, value);
    }

    public boolean isValidProModelYearFormat(String value) {
        return checkStringWithPattern(proModelYearRegex, value);
    }

    public boolean isValidProListPriceFormat(String value) {
        return checkStringWithPattern(proListPriceRegex, value);
    }

    public String getProIdFromConsole() {
        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();
        if (isValidProIdFormat(id)) {
            return id;
        }
        System.out.println("Invalid Product ID! Try again!");
        return getProIdFromConsole();
    }

    public String getProNameFromConsole() {
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        if (isValidProNameFormat(name)) {
            return name;
        }
        System.out.println("Invalid Product Name! Try again!");
        return getProNameFromConsole();
    }

    public int getProModelYearFromConsole() {
        System.out.print("Enter Product Model Year: ");
        String year = sc.nextLine();
        int y = 0;
        if (isValidProModelYearFormat(year)) {
            y = Integer.parseInt(year);
        }
        if (y > 1924 && y < 2025) {
            return y;
        }
        System.out.println("Invalid Product Model Year! Try again!");
        return getProModelYearFromConsole();
    }

    public double getProListPriceFromConsole() {
        System.out.print("Enter Product Price: ");
        String price = sc.nextLine();
        double p = 0;
        if (isValidProListPriceFormat(price)) {
            p = Double.parseDouble(price); 
        }
        if (p > 0){
            return p;
        }
        System.out.println("Invalid Product Price! Try again!");
        return getProListPriceFromConsole();
    }
    
    public boolean isExistedId(String id) {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void createNewProduct() {
        String id;
        while (true) {
            id = "P" + (rd.nextInt(900) + 100);;
            if (!isExistedId(id)) {
                break;
            }
        }
        String name = getProNameFromConsole();        
        String brandId;
        while (true) {
            brandId = brandBus.getBrandIdFromConsole();
            if (brandBus.isExistedId(brandId)) {
                break;
            }
            System.out.println("Brand ID does not exist!");
        }
        String cateId;
        while (true) {
            cateId = cateBus.getCateIdFromConsole();
            if (cateBus.isExistedId(cateId)) {
                break;
            }
            System.out.println("Category ID does not exist!");
        }
        int year = getProModelYearFromConsole();
        double price = getProListPriceFromConsole();
        Product p = new Product(id, name, brandId, cateId, year, price);
        proRepo.create(p);
        proRepo.writeDataToFile("Product.txt");
    }

    public void showProductList() {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
