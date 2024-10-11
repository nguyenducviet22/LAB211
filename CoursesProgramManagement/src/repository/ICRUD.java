/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public interface ICRUD<K, V> {
    int create(V newItem);
    HashMap<K, V> read();
    V detail(K id);
    int update(V editItem);
    int delete(K id);
}
