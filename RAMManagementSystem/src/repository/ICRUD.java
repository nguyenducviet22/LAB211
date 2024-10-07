/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
