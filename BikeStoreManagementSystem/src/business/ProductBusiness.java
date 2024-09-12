/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

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
    private String proIdRegex = "P\\d{3}";
    private String proNameRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*";
    private String proModelYearRegex = "\\d{4}";
    private String proListPriceRegex = "\\d+(\\.\\d{1,2})?";

    public ProductBusiness() {
        proRepo = new ProductRepository();
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
        System.out.print("Enter Brand ID: ");
        String id = sc.nextLine();
        if (isValidProIdFormat(id)) {
            return id;
        }
        System.out.println("Invalid Brand ID! Try again!");
        return getProIdFromConsole();
    }

    public String getProNameFromConsole() {
        System.out.print("Enter Brand Name: ");
        String name = sc.nextLine();
        if (isValidProNameFormat(name)) {
            return name;
        }
        System.out.println("Invalid Brand Name! Try again!");
        return getProNameFromConsole();
    }
    
    public int getProModelYearFromConsole() {
        System.out.print("Enter Brand Name: ");
        String year = sc.nextLine();
        if (isValidProModelYearFormat(year)) {
            return Integer.parseInt(year);
        }
        System.out.println("Invalid Brand Name! Try again!");
        return getProModelYearFromConsole();
    }
    
    public double getProListPriceFromConsole() {
        System.out.print("Enter Brand Name: ");
        String price = sc.nextLine();
        if (isValidProListPriceFormat(price)) {
            return Double.parseDouble(price);
        }
        System.out.println("Invalid Brand Name! Try again!");
        return getProListPriceFromConsole();
    }
    
    public void createNewProduct(){
        String id = getProIdFromConsole();
        String name = getProNameFromConsole();
        String brandId = brandBus.getBrandIdFromConsole();
        String cateId = cateBus.getCateIdFromConsole();
        int year = getProModelYearFromConsole();
        double price = getProListPriceFromConsole();
        Product p = new Product(id, name, brandId, cateId, year, price);
        proRepo.create(p);
    }
}
