/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ADMIN
 */
public class RAM {

    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private String production_month_year;
    private boolean active;

    public RAM() {
    }

    public RAM(String code, String type, String bus, String brand, int quantity, String production_month_year, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public RAM(String code, String type, int quantity, String production_month_year, boolean active) {
        this.code = code;
        this.type = type;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-9s | %-9s | %-10s | %4s | %25s |",
                code, type, bus, brand, quantity, production_month_year);
    }
    
    public String displaySearchByType() {
        return String.format("| %-12s | %-9s | %4s | %25s |",
                code, type, quantity, production_month_year);
    }
    
    public String displaySearchByBus() {
        return String.format("| %-12s | %-9s | %4s | %25s |",
                code, bus, quantity, production_month_year);
    }
    
    public String displaySearchByBrand() {
        return String.format("| %-12s | %-10s | %4s | %25s |",
                code, brand, quantity, production_month_year);
    }

}
