/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.HashMap;
import model.Topic;

/**
 *
 * @author ADMIN
 */
public class TopicRepository extends HashMap<String, Topic> implements ICRUD<String, Topic> {

    @Override
    public int create(Topic newItem) {
        try {
            this.put(newItem.getTopicName(), newItem);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public HashMap<String, Topic> read() {
        return this;
    }

    @Override
    public Topic detail(String id) {
        return this.get
    }

    @Override
    public int update(Topic editItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
