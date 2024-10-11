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

    public String getBusSpeedFromConsole() {
        try {
            int speed = ip.inputInt("Enter bus speed: ");
            if (speed > 0) {
                return speed + "HMz";
            }
        } catch (Exception e) {
            System.out.println("Invalid bus speed! try again");
        }
        return getBusSpeedFromConsole();
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
        System.out.println("--------------");
        System.out.println("| Bus Speed  |");
        System.out.println("|------------|");
    }

    public void showFootTable() {
        System.out.println("--------------");
    }
}
