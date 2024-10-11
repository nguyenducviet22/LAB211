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

    private String busSpeed;

    public Bus() {
    }

    public Bus(String busSpeed) {
        this.busSpeed = busSpeed;
    }

    public String getBusSpeed() {
        return busSpeed;
    }

    public void setBusSpeed(String busSpeed) {
        this.busSpeed = busSpeed;
    }

    @Override
    public String toString() {
        return String.format("| %-10s |", busSpeed);
    }

}
