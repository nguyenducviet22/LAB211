/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import model.Brand;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class BrandRepository extends HashMap<String, Brand> implements ICrud<String, Brand>{

    @Override
    public int create(Brand newItem) {
        try {
            this.put(newItem.getId(), newItem);
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public HashMap<String, Brand> read() {
        return this;
    }

    @Override
    public Brand details(String id) {
        return this.get(id);
    }

    @Override
    public int update(Brand editItem) {
        try {
            Brand oldItem = this.get(editItem.getId());
            oldItem = editItem;
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            this.remove(id);
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }
    
    // Đọc dữ liệu từ file và lưu vào Repository
    public void readDataFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            // Tạo luồng đọc dữ liệu
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;

            while ((line = bfr.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");

                // Cắt các thông tin thành phần
                String _id = stk.nextToken();
                String _name = stk.nextToken();
                String _country = stk.nextToken();

                // Tái tạo Java Object
                Brand _br = new Brand(_id, _name, _country);

                // Đưa object vào Repository
                this.create(_br);
            }

            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void writeDataToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("Repository is empty");
            return;
        }
        try {
            // Ghi file
            File f = new File(fileName);

            // Tạo ra luồng ghi file
            FileWriter fw = new FileWriter(f);

            // Tạo ra đối tượng ghi file xuống storage
            PrintWriter pw = new PrintWriter(fw);

            for (Map.Entry<String, Brand> entry : this.entrySet()) {
                System.out.println();
                Brand item = entry.getValue();
                pw.println(item.getId() + "," + item.getName() + "," + item.getCountry());
            }

            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
