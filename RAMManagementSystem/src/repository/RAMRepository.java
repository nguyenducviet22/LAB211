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
import model.RAM;

/**
 *
 * @author ADMIN
 */
public class RAMRepository extends HashMap<String, RAM> implements ICRUD<String, RAM> {

    @Override
    public int create(RAM newItem) {
        try {
            this.put(newItem.getCode(), newItem);
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public HashMap<String, RAM> read() {
        return this;
    }

    @Override
    public RAM detail(String id) {
        return this.get(id);
    }

    @Override
    public int update(RAM editItem) {
        try {
            RAM oldItem = this.get(editItem.getCode());
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
            this.get(id).setActive(false);
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
                String _code = stk.nextToken();
                String _type = stk.nextToken();
                String _bus = stk.nextToken();
                String _brand = stk.nextToken();
                String _quantity = stk.nextToken();
                String _date = stk.nextToken();
                String _active = stk.nextToken();

                // Tái tạo Java Object
                RAM _br = new RAM(_code, _type, _bus, _brand, Integer.parseInt(_quantity), _date, Boolean.parseBoolean(_active));

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

            for (Map.Entry<String, RAM> entry : this.entrySet()) {
//                System.out.println();
                RAM item = entry.getValue();
                pw.println(item.getCode() + "," + item.getType() + "," + item.getBus() + "," + item.getBrand() + "," + item.getQuantity() + "," + item.getProduction_month_year());
            }

            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
