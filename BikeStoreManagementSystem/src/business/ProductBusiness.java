/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.Brand;
import model.Category;
import model.Product;
import repository.BrandRepository;
import repository.CategoryRepository;
import repository.ProductRepository;

/**
 *
 * @author ADMIN
 */
public class ProductBusiness {

    private ProductRepository proRepo;
    private BrandRepository brandRepo;
    private CategoryRepository cateRepo;
    private BrandBusiness brandBus;
    private CategoryBusiness cateBus;
    private Inputtor ip;
    Random rd = new Random();
    Product p;

    public ProductBusiness() {
        proRepo = new ProductRepository();
        brandRepo = new BrandRepository();
        cateRepo = new CategoryRepository();
        brandBus = new BrandBusiness();
        cateBus = new CategoryBusiness();
        ip = new Inputtor();
        proRepo.readDataFromFile("Product.txt");
        brandRepo.readDataFromFile("Brand.txt");
        cateRepo.readDataFromFile("Category.txt");
        p = new Product();
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

    //To check existed or duplicated name
    public boolean isExistedName(String name) {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String getProNameFromConsole() {
        String name = ip.inputString("Enter product name: ");
        return name;
    }

    //Let user enter model year and return year value if year between 1924 and 2024
    public String getProModelYearFromConsole() {
        String year = ip.inputString("Enter Product Model Year(1924-2024): ");
        if (year.equals("")) {
            return "";
        } else {
            try {
                int y = Integer.parseInt(year);
                if (y > 1923 && y < 2025) {
                    return year;
                } else {
                    System.out.println("Product model year is not between 1924 and 2024");
                }
            } catch (Exception e) {
                System.out.println("Invalid Product Model Year! Try again!");
            }
            return getProModelYearFromConsole();
        }
    }

    //Let user enter price and return price value if price is positive
    public String getProListPriceFromConsole() {
        String price = ip.inputString("Enter Product Price: ");
        if (price.equals("")) {
            return "";
        } else {
            try {
                double p = Double.parseDouble(price);
                if (p > 0) {
                    return price;
                } else {
                    System.out.println("Product price is not a positive number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid Product Price! Try again!");
            }
        }
        return getProListPriceFromConsole();
    }

    //To check existed or duplicated ID
    public boolean isExistedId(String id) {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //To create a new product
    public void createNewProduct() {
        String id;
        //To check duplicated
        while (true) {
            id = "P" + (rd.nextInt(900) + 100);
            if (!isExistedId(id)) {
                break;
            }
        }
        //To enter product name
        String name = getProNameFromConsole();
        while (name.equals("")) {
            System.out.println("Product name can not empty!");
            name = getProNameFromConsole();
        }
        //To enter brand ID for product
        brandBus.showBrandList();
        String brandId = brandBus.getBrandIdFromConsole();
        //To enter category ID for product
        cateBus.showCateList();
        String cateId = cateBus.getCateIdFromConsole();
        //To enter model year
        String year = getProModelYearFromConsole();
        while (year.equals("")) {
            System.out.println("Product model year can not empty!");
            year = getProModelYearFromConsole();
        }
        //To enter price 
        String price = getProListPriceFromConsole();
        while (price.equals("")) {
            System.out.println("Product price can not empty!");
            price = getProListPriceFromConsole();
        }
        Product p = new Product(id, name, brandId, cateId, Integer.parseInt(year), Double.parseDouble(price));
        proRepo.create(p);
        System.out.println("Create successfully!");
    }

    //To search a product by entering a part of name, ignore case
    public void searchProByName() {
        String name = ip.inputString("Enter a part of product name to search: ").toLowerCase();
        if (name.length() < 5){
            name = ip.inputString("Enter a part of product name to search: ").toLowerCase();
        }
        List<Product> list = new ArrayList<>();
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(name)) {
                list.add(entry.getValue());
            }
        }
        if (list.isEmpty()) {
            System.out.println("Have no any Product!");
        } else {
            sortAscByYear(list);
        }
    }

    public void sortAscByYear(List<Product> list) {
        Comparator<Product> c = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getModel_year() - o2.getModel_year();
            }
        };
        Collections.sort(list, c);
        showProductList(list);
    }

    //To update the product by entering product id
    public void updateProduct() {
        showProductList();
        Product p;
        String proID;
        //To check existing for inputted id by user
        while (true) {
            proID = ip.inputString("Enter product id to update: ").toUpperCase();
            if (isExistedId(proID)) {
                p = proRepo.details(proID);
                break;
            }
            System.out.println("Product does not exist!");
        }
        showHeadTable();
        System.out.println(p);
        showFootTable();
        //Delete the current product
        proRepo.delete(proID);

        String choice;
        String temp;
        try {
            do {
                System.out.println("");
                System.out.println("--UPDATE INFO--");
                System.out.println("1. Product name");
                System.out.println("2. Brand name");
                System.out.println("3. Category name");
                System.out.println("4. Model year");
                System.out.println("5. Price");
                System.out.println("Other. Finished!");

                while (true) {
                    choice = ip.inputString("Choose info to update: ");
                    if (!choice.trim().equals("")) {
                        break;
                    }
                    System.out.println("Choose again!");
                }

                switch (choice) {
                    case "1":
                        temp = getProNameFromConsole();
                        //Check empty, if inputted name is empty, keeping the old value
                        if (temp.equals("")) {
                            System.out.println("Product name has been unchanged!");
                            p.setName(p.getName());
                        } else {
                            p.setName(temp);
                        }
                        break;
                    case "2":
                        brandBus.showBrandList();
                        temp = brandBus.getBrandNameFromConsole();
                        //Check empty, if inputted brand id is empty, keeping the old value
                        if (temp.equals("")) {
                            System.out.println("Brand name of product has been unchanged!");
                            p.setBrand_id(p.getBrand_id());
                        } else {
                            p.setBrand_id(temp);
                        }
                        break;
                    case "3":
                        cateBus.showCateList();
                        temp = cateBus.getCateNameFromConsole();
                        //Check empty, if inputted cate id is empty, keeping the old value
                        if (temp.equals("")) {
                            System.out.println("Category name of product has been unchanged!");
                            p.setCategory_id(p.getCategory_id());
                        } else {
                            p.setCategory_id(temp);
                        }
                        break;
                    case "4":
                        temp = getProModelYearFromConsole();
                        //Check empty, if inputted model year is empty, keeping the old value
                        if (temp.equals("")) {
                            System.out.println("Product model year has been unchanged!");
                            p.setModel_year(p.getModel_year());
                        } else {
                            p.setModel_year(Integer.parseInt(temp));
                        }
                        break;
                    case "5":
                        temp = ip.inputString("Enter product price: ");
                        //Check empty, if inputted price is empty, keeping the old value
                        if (temp.equals("")) {
                            System.out.println("Product price has been unchanged!");
                            p.setList_price(p.getList_price());
                        } else {
                            p.setList_price(Double.parseDouble(temp));
                        }
                        break;
                }
            } while (Integer.parseInt(choice) < 6 && Integer.parseInt(choice) > 0);
        } catch (Exception e) {
        }
        //Re-create a new product
        proRepo.create(p);
        System.out.println("Update successfullly!");
    }

    //To delete a product by inputted product id by user
    public void deleteProduct() {
        showProductList();
        String proID;
        //To check existing product id
        while (true) {
            proID = ip.inputString("Enter product id to delete: ").toUpperCase();
            if (isExistedId(proID)) {
                showHeadTable();
                System.out.println(proRepo.details(proID));
                showFootTable();
                //To confirm before deleting
                if (confirm()) {
                    proRepo.delete(proID);
                    System.out.println("Delete successfully!");
                }
                break;
            } else {
                System.out.println("Product ID does not exist!");
            }
        }
    }

    //To confirm before deleting the product
    public boolean confirm() {
        String choice = ip.inputString("Confirm to delete this product!\n 1. Yes\n 2. No\n");
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

    //Convert brand id to name
    private String getBrandNameById(String brandId) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getId().equals(brandId)) {
                return entry.getValue().getName();
            }
            if (entry.getValue().getName().equals(brandId)) {
                return entry.getValue().getName();
            }
        }
        return null;
    }

    //Convert cate id to name
    private String getCateNameById(String cateId) {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            if (entry.getValue().getId().equals(cateId)) {
                return entry.getValue().getName();
            }
            if (entry.getValue().getName().equals(cateId)) {
                return entry.getValue().getName();
            }
        }
        return null;
    }

    //Convert id to name (to read from file)
    private void transIdToName() {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            String brandName = getBrandNameById(entry.getValue().getBrand_id());
            entry.getValue().setBrand_id(brandName);
            String cateName = getCateNameById(entry.getValue().getCategory_id());
            entry.getValue().setCategory_id(cateName);
        }
    }

    //To show the product list from file to console 
    //Brand and cate ID must be converted to NAME first
    //Cuz saved brand and cate info is ID but showed as NAME
    //Used for Update and Delete methods
    public void showProductList() {
        transIdToName();
        List<Product> list = new ArrayList<>();
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            list.add(entry.getValue());
        }
        if (list.isEmpty()) {
            System.out.println("Have no any Product!");
        } else {
            sortDescByPriceAscByName(list);
        }
    }

    //To order by list price desc and name asc
    public void sortDescByPriceAscByName(List<Product> list) {
        Comparator<Product> c = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int priceCom = (int) (o2.getList_price() - o1.getList_price());
                //if the prices are not the same
                if (priceCom != 0) {
                    return priceCom;
                }
                //if the prices are the same
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(list, c);

        showProductList(list);
    }

    //To show the product list from file
    //Used for Search and Sort methods
    public void showProductList(List<Product> list) {
        transIdToName();
        showHeadTable();
        for (Product product : list) {
            System.out.println(product);
        }
        showFootTable();
    }

    //To convert brand name to id
    private String getBrandIdByName(String brandName) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getName().equals(brandName)) {
                return entry.getValue().getId();
            }
        }
        return null;
    }

    //To convert cate name to id
    private String getCateIdByName(String cateName) {
        for (Map.Entry<String, Category> entry : cateRepo.entrySet()) {
            if (entry.getValue().getName().equals(cateName)) {
                return entry.getValue().getId();
            }
        }
        return null;
    }

    //To convert name to id (to write to file)
    private void transNameToId() {
        for (Map.Entry<String, Product> entry : proRepo.entrySet()) {
            String brandId = getBrandIdByName(entry.getValue().getBrand_id());
            entry.getValue().setBrand_id(brandId);
            String cateId = getCateIdByName(entry.getValue().getCategory_id());
            entry.getValue().setCategory_id(cateId);
        }
    }

    //To write data from console to file
    //Brand and cate NAME must be converted to ID first
    //Cuz saved band and cate info is ID but showed as NAME
    public void writeToFile() {
        transNameToId();
        proRepo.writeDataToFile("Product.txt");
        System.out.println("Save to file successfully!");
    }

    public void showHeadTable() {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("| ID   | Product Name             | Brand Name  | Category Name  | Model  | Price (USD)  |");
        System.out.println("|------|--------------------------|-------------|----------------|--------|--------------|");
    }

    public void showFootTable() {
        System.out.println("------------------------------------------------------------------------------------------");
    }
}
