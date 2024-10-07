/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Bus;
import repository.BusRepository;

/**
 *
 * @author ADMIN
 */
public class BusBusiness {

    private BusRepository busRepo;
    private String busCodeRegex = "S\\d{3}";
    Random rd = new Random();
    Inputtor ip;

    public BusBusiness() {
        busRepo = new BusRepository();
        ip = new Inputtor();
        busRepo.readDataFromFile("Bus.txt");
    }

    public BusBusiness(BusRepository busRepo) {
        this.busRepo = busRepo;
    }

    public boolean checkStringWithPattern(String regexPattern, String value) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isValidBusCode(String value) {
        return checkStringWithPattern(busCodeRegex, value);
    }

    public boolean isExistedCode(String id) {
        for (Map.Entry<String, Bus> entry : busRepo.entrySet()) {
            if (entry.getValue().getBusCode().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String generateBusCode() {
        String code = "S" + rd.nextInt(900);
        if (isValidBusCode(code)) {
            if (!isExistedCode(code)) {
                return code;
            }
        }
        return generateBusCode();
    }

    public String getBusCodeFromConsole() {
        String code = ip.inputString("Enter bus code: ");
        if (isValidBusCode(code)) {
            if (isExistedCode(code)) {
                return code;
            }
        }
        System.out.println("Not found bus code!");
        return getBusCodeFromConsole();
    }

    public int getBusSpeedFromConsole() {
        try {
            int speed = ip.inputInt("Enter bus speed: ");
            if (speed > 0) {
                return speed;
            }
        } catch (Exception e) {
            System.out.println("Invalid bus speed! try again");
        }
        return getBusSpeedFromConsole();
    }

    public void addNewBus() {
        String code = generateBusCode();
        int speed = getBusSpeedFromConsole();

        Bus b = new Bus(code, speed);
        busRepo.create(b);
    }

    public void showBusList() {
        if (busRepo.entrySet().isEmpty()) {
            System.out.println("Bus list is empty!");
        } else {
            showHeadTable();
            for (Map.Entry<String, Bus> entry : busRepo.entrySet()) {
                System.out.println(entry.getValue());
            }
            showFootTable();
        }
    }
    
    public void showHeadTable() {
        System.out.println("---------------------");
        System.out.println("| Code | Bus Speed  |");
        System.out.println("|-------------------|");
    }

    public void showFootTable() {
        System.out.println("---------------------");
    }
}
