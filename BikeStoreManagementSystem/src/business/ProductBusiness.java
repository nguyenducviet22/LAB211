/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
    private Inputtor input;
    Random rd = new Random();
    private String proIdRegex = "P\\d{3}";
    private String proNameRegex = "[A-Z][a-z]+(\\s[A-Z][a-z]+)*(\\s\\d+)*";
    private String proModelYearRegex = "\\d{4}";
    private String proListPriceRegex = "^\\d+(\\.\\d+)?$";

    public ProductBusiness() {
        proRepo = new ProductRepository();
        brandBus = new BrandBusiness();
        cateBus = new CategoryBusiness();
        input = new Inputtor();
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
        String id = input.inputString();
        if (isValidProIdFormat(id)) {
            return id;
        }
        System.out.println("Invalid Product ID! Try again!");
        return getProIdFromConsole();
    }

    public String getProNameFromConsole() {
        System.out.print("Enter Product Name: ");
        String name = input.inputString();
        if (isValidProNameFormat(name)) {
            return name;
        }
        System.out.println("Invalid Product Name! Try again!");
        return getProNameFromConsole();
    }

    public int getProModelYearFromConsole() {
        System.out.print("Enter Product Model Year(1924-2024): ");
        String year = input.inputString();
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
        String price = input.inputString();
        double p = 0;
        if (isValidProListPriceFormat(price)) {
            p = Double.parseDouble(price);
        }
        if (p > 0) {
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
            brandBus.showBrandList();
            brandId = brandBus.getBrandIdFromConsole();
            if (brandBus.isExistedId(brandId)) {
                break;
            }
            System.out.println("Brand ID does not exist!");
        }
        String cateId;
        while (true) {
            cateBus.showCateList();
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
        showProductList();
    }

    public void searchProByName() {
        System.out.print("Enter name to search: ");
        String name = input.inputString();
        HashMap<String, Product> hm = new HashMap();
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            if (entry.getValue().getName().contains(name)) {
                hm.put(entry.getKey(), entry.getValue());
            }
        }
        if (hm.isEmpty()) {
            System.out.println("Products do not exist! Try another products name! ");
            System.out.println("");
        } else {
            showHeadTable();
            for (Map.Entry<String, Product> entry : hm.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }

    public void updateProduct() {
        showProductList();
        Product p;
        String proID;
        while (true) {
            System.out.print("Enter product id to update: ");
            proID = input.inputString();
            if (isExistedId(proID)) {
                p = proRepo.details(proID);
                break;
            }
            System.out.println("Product ID does not exist!");
        }
        showHeadTable();
        System.out.println(p);
        showFootTable();
        proRepo.delete(proID);
        createNewProduct();
        System.out.println("Update successfullly!");
        System.out.println("");
    }

    public void deleteProduct() {
        showProductList();
        Product p;
        String proID;
        while (true) {
            System.out.print("Enter product id to delete: ");
            proID = input.inputString();
            if (isExistedId(proID)) {
                showHeadTable();
                System.out.println(proRepo.details(proID));
                showFootTable();
                if (confirm()) {
                    proRepo.delete(proID);
                    proRepo.writeDataToFile("Product.txt");
                    showProductList();
                    System.out.println("Delete successfully!");
                    System.out.println("");
                }
                break;
            } else {
                System.out.println("Product ID does not exist!");
            }
        }
    }

    public boolean confirm() {
        System.out.println("Confirm to delete this product: ");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String choice = input.inputString();
        switch (choice) {
            case "1":
                return true;
            case "2":
                System.out.println("Deletion has been cancaled!");
                return false;
            default:
                System.out.println("Invalid selection!");
        }
        return confirm();
    }

    public void sortProduct() {
        System.out.println("1. Sort by ID ascending");
        System.out.println("2. Sort by Name ascending");
        System.out.println("3. Sort by Model Year descending");
        System.out.println("4. Sort by Price descending");
        System.out.println("0. Back to product menu");
        System.out.print("Choose kinds of sorting: ");

        String choice = input.inputString();
        switch (choice) {
            case "1":
                sortByID();
                break;
            case "2":
                sortByName();
                break;
            case "3":
                sortByYear();
                break;
            case "4":
                sortByPrice();
                break;
            case "0":
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    public void sortByID() {
        for (Map.Entry<String, Product> ei : proRepo.entrySet()) {
            for (Map.Entry<String, Product> ej : proRepo.entrySet()) {
                if (ei.getValue().getId().compareTo(ej.getValue().getId()) < 0) {
                    Product temp = ei.getValue();
                    ei.setValue(ej.getValue());
                    ej.setValue(temp);
                }
            }
        }
        showProductList();
    }

    public void sortByName() {
        for (Map.Entry<String, Product> ei : proRepo.entrySet()) {
            for (Map.Entry<String, Product> ej : proRepo.entrySet()) {
                if (ei.getValue().getName().compareTo(ej.getValue().getName()) < 0) {
                    Product temp = ei.getValue();
                    ei.setValue(ej.getValue());
                    ej.setValue(temp);
                }
            }
        }
        showProductList();
    }

    public void sortByYear() {
        for (Map.Entry<String, Product> ei : proRepo.entrySet()) {
            for (Map.Entry<String, Product> ej : proRepo.entrySet()) {
                if (ei.getValue().getModel_year() > (ej.getValue().getModel_year())) {
                    Product temp = ei.getValue();
                    ei.setValue(ej.getValue());
                    ej.setValue(temp);
                }
            }
        }
        showProductList();
    }

    public void sortByPrice() {
        for (Map.Entry<String, Product> ei : proRepo.entrySet()) {
            for (Map.Entry<String, Product> ej : proRepo.entrySet()) {
                if (ei.getValue().getList_price() > (ej.getValue().getList_price())) {
                    Product temp = ei.getValue();
                    ei.setValue(ej.getValue());
                    ej.setValue(temp);
                }
            }
        }
        showProductList();
    }

    public void showProductList() {
        showHeadTable();
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            System.out.println(entry.getValue());
        }
        showFootTable();
    }

    public void showHeadTable() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("| ID   | Product Name             | Brand ID  | Category ID  | Model  | Price (USD)  |");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void showFootTable() {
        System.out.println("--------------------------------------------------------------------------------------");
    }
}
