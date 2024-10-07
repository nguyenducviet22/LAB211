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
public class Brand {
    private String brandCode;
    private String brandName;
    private String country;

    public Brand() {
    }

    public Brand(String brandCode, String brandName, String country) {
        this.brandCode = brandCode;
        this.brandName = brandName;
        this.country = country;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("| %-4s | %-10s | %-12s |", brandCode, brandName, country);
    }
    
    
}
