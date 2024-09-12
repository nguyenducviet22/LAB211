/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.HashMap;

/**
 *
 * @author NGUYEN DUC VIET
 */
public interface ICrud<K, V> {
    int create(V newItem);
    HashMap<K, V> read();
    V details(K id);
    int update(V editItem);
    int delete(K id);
}
