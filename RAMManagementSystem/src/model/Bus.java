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
public class Bus {

    private String busCode;
    private int busSpeed;

    public Bus() {
    }

    public Bus(String busCode, int busSpeed) {
        this.busCode = busCode;
        this.busSpeed = busSpeed;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public int getBusSpeed() {
        return busSpeed;
    }

    public void setBusSpeed(int busSpeed) {
        this.busSpeed = busSpeed;
    }

    @Override
    public String toString() {
        return String.format("| %-4s | %-10s |", busCode, busSpeed);
    }

}
