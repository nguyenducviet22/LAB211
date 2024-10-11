/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.RAM;
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
        String typeName = typeBus.getTypeNameFromConsole();
        busBus.showBusList();
        String busName = busBus.getBusSpeedFromConsole();
        brandBus.showBrandList();
        String brandName = brandBus.getBrandNameFromConsole();
        String qty = getQuantityFromConsole();
        while (qty.equals("")) {
            System.out.println("Quantity can not be empty!");
            qty = getQuantityFromConsole();
        }
        String date = getProductionDateFromConsole();
        while (date.equals("")) {
            System.out.println("Production date can not be empty!");
            date = getProductionDateFromConsole();
        }
        String code;
        while (true) {
            code = "RAM" + typeName + "_" + (rd.nextInt(100) + 1);
            if (!isExistedCode(code)) {
                break;
            }
        }
        RAM r = new RAM(code, typeName, busName, brandName, Integer.parseInt(qty), date, true);
        ramRepo.create(r);
    }

    private void searchByType() {
        typeBus.showTypeList();
        List<RAM> list = new ArrayList<>();
        String typeName = ip.inputString("Enter a part of type name to search: ").toUpperCase();
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().isActive()) {
                if (entry.getValue().getType().contains(typeName)) {
                    list.add(entry.getValue());
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println("Have no any Ram!");
        } else {
            showHeadSearchTypeTable();
            for (RAM ram : list) {
                System.out.println(ram.displaySearchByType());
            }
            showFootSearchTypeTable();
        }
    }

    private void searchByBus() {
        busBus.showBusList();
        List<RAM> list = new ArrayList<>();
        String bus = ip.inputString("Enter bus speed to search: ");
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().isActive()) {
                if (entry.getValue().getBus().contains(bus)) {
                    list.add(entry.getValue());
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println("Have no nay Ram!");
        } else {
            showHeadSearchBusTable();
            for (RAM ram : list) {
                System.out.println(ram.displaySearchByBus());
            }
            showFootSearchBusTable();
        }
    }

    private void searchByBrand() {
        brandBus.showBrandList();
        List<RAM> list = new ArrayList<>();
        String brand = ip.inputString("Enter a part of brand name to search: ").toLowerCase();
        for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
            if (entry.getValue().isActive()) {
                if (entry.getValue().getBrand().toLowerCase().contains(brand)) {
                    list.add(entry.getValue());
                }
            }
        }

        if (list.isEmpty()) {
            System.out.println("Have no nay Ram!");
        } else {
            showHeadSearchBrandTable();
            for (RAM ram : list) {
                System.out.println(ram.displaySearchByBrand());
            }
            showFootSearchBrandTable();
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

        do {
            System.out.println("");
            System.out.println("--UPDATE INFO--");
            System.out.println("1. Update type name");
            System.out.println("2. Update bus speed");
            System.out.println("3. Update brand name");
            System.out.println("4. Update quantity");
            System.out.println("5. Update production date");
            System.out.println("6. Update active");
            System.out.println("0. Finish!");

            while (true) {
                choice = ip.inputString("Enter your choice: ");
                if (!choice.equals("")) {
                    break;
                }
            }

            switch (choice) {
                case "1":
                    typeBus.showTypeList();
                    temp = typeBus.getTypeNameFromConsole();
                    if (temp.equals("")) {
                        System.out.println("Type has been unchanged!");
                    } else {
                        typeName = temp;
                        code = "RAM" + typeName + code.substring(7);
                    }
                    break;
                case "2":
                    busBus.showBusList();
                    temp = busBus.getBusSpeedFromConsole();
                    if (temp.equals("")) {
                        System.out.println("Bus has been unchanged!");
                    } else {
                        busName = temp;
                    }
                    break;
                case "3":
                    brandBus.showBrandList();
                    temp = brandBus.getBrandNameFromConsole();
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
                    temp = ip.inputString(" 1. Active\n 2. Inactive\n");
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
                case "0":
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
        ramRepo.create(new RAM(code, typeName, busName, brandName, qty, date, active));
        System.out.println("Update successfully!");
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

    public void showAllItems() {
        if (ramRepo.entrySet().isEmpty()) {
            System.out.println("RAM list is empty!");
        } else {
            List<RAM> list = new ArrayList<>();
            for (Map.Entry<String, RAM> entry : ramRepo.entrySet()) {
                if (entry.getValue().isActive()) {
                    list.add(entry.getValue());
                }
            }
            sortBySubMenu(list);
        }
    }

    private void sortBySubMenu(List<RAM> list) {
        String choice;
        do {
            System.out.println("");
            System.out.println("1. Sort by type");
            System.out.println("2. Sort by bus");
            System.out.println("3. Sort by brand");
            System.out.println("0. Back to the main menu");
            choice = ip.inputString("Enter your choice: ");
            switch (choice) {
                case "1":
                    sortByType(list);
                    break;
                case "2":
                    sortByBus(list);
                    break;
                case "3":
                    sortByBrand(list);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (!choice.equals("0"));
    }

    private void sortByType(List<RAM> list) {
        Comparator<RAM> c = new Comparator<RAM>() {
            @Override
            public int compare(RAM o1, RAM o2) {
                return o1.getType().compareTo(o2.getType());
            }
        };
        Collections.sort(list, c);
        showAllItems(list);
    }

    private void sortByBus(List<RAM> list) {
        Comparator<RAM> c = new Comparator<RAM>() {
            @Override
            public int compare(RAM o1, RAM o2) {
                return o1.getBus().compareTo(o2.getBus());
            }
        };
        Collections.sort(list, c);
        showAllItems(list);
    }

    private void sortByBrand(List<RAM> list) {
        Comparator<RAM> c = new Comparator<RAM>() {
            @Override
            public int compare(RAM o1, RAM o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        };
        Collections.sort(list, c);
        showAllItems(list);
    }

    private void showAllItems(List<RAM> list) {
        showHeadTable();
        for (RAM ram : list) {
            if (ram.isActive()) {
                System.out.println(ram);
            }
        }
        showFootTable();
    }

    public void storeDataToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showHeadTable() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("| RAM Code     | Type Name | Bus Speed | Brand Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void showFootTable() {
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void showHeadSearchTypeTable() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("| RAM Code     | Type Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("---------------------------------------------------------------");
    }

    public void showFootSearchTypeTable() {
        System.out.println("---------------------------------------------------------------");
    }

    public void showHeadSearchBusTable() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("| RAM Code     | Bus Name  | Qty  | Production date (mm-yyyy) |");
        System.out.println("---------------------------------------------------------------");
    }

    public void showFootSearchBusTable() {
        System.out.println("---------------------------------------------------------------");
    }

    public void showHeadSearchBrandTable() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("| RAM Code     | Brand Name | Qty  | Production date (mm-yyyy) |");
        System.out.println("----------------------------------------------------------------");
    }

    public void showFootSearchBrandTable() {
        System.out.println("----------------------------------------------------------------");
    }

}
