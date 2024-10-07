/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.Brand;
import model.Bus;
import model.RAM;
import model.Type;
import repository.BrandRepository;
import repository.BusRepository;
import repository.RAMRepository;
import repository.TypeRepository;

/**
 *
 * @author ADMIN
 */
public class RAMBusiness {

    private RAMRepository ramRepo;
    private Inputtor ip;
    Random rd = new Random();
    private TypeBusiness typeBus;
    private BusBusiness busBus;
    private BrandBusiness brandBus;
    private TypeRepository typeRepo;
    private BrandRepository brandRepo;
    private BusRepository busRepo;
    private List<RAM> list;
    private RAM r;

    public RAMBusiness() {
        ramRepo = new RAMRepository();
        ip = new Inputtor();
        typeBus = new TypeBusiness();
        busBus = new BusBusiness();
        brandBus = new BrandBusiness();
        typeRepo = new TypeRepository();
        brandRepo = new BrandRepository();
        busRepo = new BusRepository();
        list = new ArrayList<>();
        r = new RAM();
        ramRepo.readDataFromFile("Ram.txt");
        typeRepo.readDataFromFile("Type.txt");
        brandRepo.readDataFromFile("Brand.txt");
        busRepo.readDataFromFile("Bus.txt");
    }

    public RAMBusiness(RAMRepository ramRepo) {
        this.ramRepo = ramRepo;
    }

    public RAMRepository getRamRepo() {
        return ramRepo;
    }

    public void setRamRepo(RAMRepository ramRepo) {
        this.ramRepo = ramRepo;
    }

    public boolean isExistedCode(String code) {
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public String getQuantityFromConsole() {
        String qty = ip.inputString("Enter RAM quantity: ");
        if (qty.equals("")) {
            return "";
        } else {
            try {
                int q = Integer.parseInt(qty);
                if (q > 0) {
                    return qty;
                } else {
                    System.out.println("Quantity must be a positive number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid quantity! try again");
            }
        }
        return getQuantityFromConsole();
    }

    public String getProductionDateFromConsole() {
        String month = ip.inputString("Enter RAM production month: ");
        String year = ip.inputString("Enter RAM production year: ");
        if (month.equals("") || year.equals("")) {
            return "";
        } else {
            try {
                int m = Integer.parseInt(month);
                int y = Integer.parseInt(year);
                if (m > 0 && m < 13 && y > 1923 && y < 2025) {
                    String date = m + "-" + y;
                    return date;
                } else {
                    System.out.println("Month or Year does not exist!");
                }
            } catch (Exception e) {
                System.out.println("Invalid production date! try again");
            }
        }
        return getProductionDateFromConsole();
    }

    public void addAnItem() {
        typeBus.showTypeList();
        String typeCode = typeBus.getTypeCodeFromConsole();
        busBus.showBusList();
        String busCode = busBus.getBusCodeFromConsole();
        brandBus.showBrandList();
        String brandCode = brandBus.getBrandCodeFromConsole();
        String qty = getQuantityFromConsole();
        while (qty.equals("")) {
            System.out.println("Quantity can not be empty!");
            qty = getQuantityFromConsole();
        }
        //check valid mm-yyyy
        String date = getProductionDateFromConsole();
        while (date.equals("")) {
            System.out.println("Production date can not be empty!");
            date = getProductionDateFromConsole();
        }
        String code;
        while (true) {
            code = "RAM" + typeCode + "_" + (rd.nextInt(100) + 1);
            if (!isExistedCode(code)) {
                break;
            }
        }
        RAM r = new RAM(code, typeCode, busCode, brandCode, Integer.parseInt(qty), date, true);
        ramRepo.create(r);
    }

    private void searchByType() {
        typeBus.showTypeList();
        transCodetoName();
        String typeName = ip.inputString("Enter a part of type name to search: ").toLowerCase();
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().isActive()) {
                if (entry.getValue().getType().toLowerCase().contains(typeName)) {
                    //how to use toStringForSearch()
                    r = new RAM(entry.getValue().getCode(), entry.getValue().getType(), entry.getValue().getQuantity(), entry.getValue().getProduction_month_year(), true);
                    list.add(r);
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println("Have no any Ram!");
        } else {
            showHeadSearchTypeTable();
            showAllItems(list);
            showFootSearchTypeTable();
        }
    }

    private void searchByBus() {
        String bus = ip.inputString("Enter type name: ");
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().getBus().contains(bus)) {
                list.add(entry.getValue());
            }
        }
    }

    private void searchByBrand() {
        String brand = ip.inputString("Enter type name: ");
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().getBrand().contains(brand)) {
                list.add(entry.getValue());
            }
        }
    }

    public void searchOnSubMenu() {
        String choice;
        do {
            System.out.println("1. Search By Type");
            System.out.println("2. Search By Bus");
            System.out.println("3. Search By Brand");
            System.out.println("0. Back to the main menu");
            choice = ip.inputString("Enter your choice: ");
            switch (choice) {
                case "1":
                    searchByType();
                    break;
                case "2":
                    searchByBus();
                    break;
                case "3":
                    searchByBrand();
                    break;
                default:

            }
        } while (!choice.equals("0"));

    }

    public void updateItemInfo() {
        showAllItems();
        String code;
        while (true) {
            code = ip.inputString("Enter RAM code to update: ").toUpperCase();
            if (isExistedCode(code)) {
                r = ramRepo.detail(code);
                break;
            }
            System.out.println("RAM does not exist!");
        }
        showHeadTable();
        System.out.println(r);
        showFootTable();
        ramRepo.delete(code);

        String choice;
        String temp;
        String typeName = r.getType();
        String busName = r.getBus();
        String brandName = r.getBrand();
        int qty = r.getQuantity();
        String date = r.getProduction_month_year();
        boolean active = true;
        try {
            do {
                System.out.println("");
                System.out.println("--UPDATE INFO--");
                System.out.println("1. Update type name");
                System.out.println("2. Update bus speed");
                System.out.println("3. Update brand name");
                System.out.println("4. Update quantity");
                System.out.println("5. Update production date");
                System.out.println("6. Update active");
                System.out.println("Other. Finish!");

                while (true) {
                    choice = ip.inputString("Enter your choice: ");
                    if (!choice.equals("")) {
                        break;
                    }
                }

                switch (choice) {
                    case "1":
                        typeBus.showTypeList();
                        temp = typeBus.getTypeCodeFromConsole();
                        if (temp.equals("")) {
                            System.out.println("Type has been unchanged!");
                        } else {
                            typeName = temp;
                            code = "RAM" + typeName + code.substring(7);
                        }
                        break;
                    case "2":
                        busBus.showBusList();
                        temp = busBus.getBusCodeFromConsole();
                        if (temp.equals("")) {
                            System.out.println("Bus has been unchanged!");
                        } else {
                            busName = temp;
                        }
                        break;
                    case "3":
                        brandBus.showBrandList();
                        temp = brandBus.getBrandCodeFromConsole();
                        if (temp.equals("")) {
                            System.out.println("Brand has been unchanged!");
                        } else {
                            brandName = temp;
                        }
                        break;
                    case "4":
                        temp = getQuantityFromConsole();
                        if (temp.equals("")) {
                            System.out.println("Quantity has been unchanged!");
                        } else {
                            qty = Integer.parseInt(temp);
                        }
                        break;
                    case "5":
                        temp = getProductionDateFromConsole();
                        if (temp.equals("")) {
                            System.out.println("Production date has been unchanged!");
                        } else {
                            date = temp;
                        }
                        break;
                    case "6":
                        temp = ip.inputString("1. Active\n 2. Inactive\n");
                        if (temp.equals("")) {
                            System.out.println("Active has been unchanged!");
                        } else if (temp.equals("1")) {
                            active = true;
                        } else if (temp.equals("2")) {
                            active = false;
                        } else {
                            System.out.println("Invalid selection!");
                        }
                        break;
                }
            } while (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) < 7);
        } catch (Exception e) {
            ramRepo.create(new RAM(code, typeName, busName, brandName, qty, date, active));
            System.out.println("Update successfully!");
        }
    }

    public void deleteItem() {
        showAllItems();
        String id;
        while (true) {
            id = ip.inputString("Enter RAM code to delete: ").toUpperCase();
            if (isExistedCode(id)) {
                showHeadTable();
                System.out.println(ramRepo.detail(id));
                showFootTable();
                if (confirm()) {
                    ramRepo.delete(id);
                    System.out.println("Delete successfully");
                }
                break;
            } else {
                System.out.println("Not found!");
            }
        }
    }

    private boolean confirm() {
        String cf = ip.inputString("Are you sure?\n 1. Yes\n 0. No\n");
        if (cf.equals("1")) {
            return true;
        } else if (cf.equals("0")) {
            System.out.println("Deletion has been canceled!");
            return false;
        } else {
            System.out.println("Invalid selection!");
            return confirm();
        }
    }

    private String getTypeNameByCode(String code) {
        for (Map.Entry<String, Type> entry : typeRepo.entrySet()) {
            if (entry.getValue().getTypeCode().equals(code)) {
                return entry.getValue().getTypeName();
            }
            if (entry.getValue().getTypeName().equals(code)) {
                return entry.getValue().getTypeName();
            }
        }
        return null;
    }

    private String getBusSpeedByCode(String code) {
        for (Map.Entry<String, Bus> entry : busRepo.entrySet()) {
            if (entry.getValue().getBusCode().equals(code)) {
                return entry.getValue().getBusSpeed() + "";
            }
            if ((entry.getValue().getBusSpeed() + "MHz").equals(code)) {
                return entry.getValue().getBusSpeed() + "";
            }
        }
        return null;
    }

    private String getBrandNameByCode(String code) {
        for (Map.Entry<String, Brand> entry : brandRepo.entrySet()) {
            if (entry.getValue().getBrandCode().equals(code)) {
                return entry.getValue().getBrandName();
            }
            if (entry.getValue().getBrandName().equals(code)) {
                return entry.getValue().getBrandName();
            }
        }
        return null;
    }

    private void transCodetoName() {
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            String typeName = getTypeNameByCode(entry.getValue().getType());
            entry.getValue().setType(typeName);
            String busSpeed = getBusSpeedByCode(entry.getValue().getBus());
            entry.getValue().setBus(busSpeed + "MHz");
            String brandName = getBrandNameByCode(entry.getValue().getBrand());
            entry.getValue().setBrand(brandName);
        }
    }

    public void showAllItems() {
        if (ramRepo.entrySet().isEmpty()) {
            System.out.println("RAM list is empty!");
        } else {
            transCodetoName();
            showHeadTable();
            for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
                if (entry.getValue().isActive()) {
                    System.out.println(entry.getValue());
                }
            }
            showFootTable();
        }
    }

    private void showAllItems(List<RAM> list) {
        for (RAM ram : list) {
            if (ram.isActive()) {
                System.out.println(ram);
            }
        }
    }

    public void storeDataToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showHeadTable() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("| RAM Code   | Type Name | Bus Speed | Brand Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("|------------------------------------------------------------------------------------|");
    }

    public void showFootTable() {
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void showHeadSearchTypeTable() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("| RAM Code   | Type Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("-------------------------------------------------------------");
    }

    public void showFootSearchTypeTable() {
        System.out.println("-------------------------------------------------------------");
    }

    public void showHeadSearchBusTable() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("| RAM Code   | Bus Name  | Qty  | Production date (mm-yyyy) |");
        System.out.println("-------------------------------------------------------------");
    }

    public void showFootSearchBusTable() {
        System.out.println("-------------------------------------------------------------");
    }

    public void showHeadSearchBrandTable() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("| RAM Code   | Brand Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("--------------------------------------------------------------");
    }

    public void showFootSearchBrandTable() {
        System.out.println("--------------------------------------------------------------");
    }

}
