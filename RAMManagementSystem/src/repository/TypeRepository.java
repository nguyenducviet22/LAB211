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
import model.Type;

/**
 *
 * @author ADMIN
 */
public class TypeRepository extends HashMap<String, Type> implements ICRUD<String, Type> {

    @Override
    public int create(Type newItem) {
        try {
            this.put(newItem.getTypeCode(), newItem);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public HashMap<String, Type> read() {
        return this;
    }

    @Override
    public Type detail(String id) {
        return this.get(id);
    }

    @Override
    public int update(Type editItem) {
        try {
            Type oldType = this.get(editItem.getTypeCode());
            oldType = editItem;
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        try {
            this.remove(id);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                String _name = stk.nextToken();

                // Tái tạo Java Object
                Type _br = new Type(_code, _name);

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

            for (Map.Entry<String, Type> entry : this.entrySet()) {
//                System.out.println();
                Type item = entry.getValue();
                pw.println(item.getTypeCode()+ "," + item.getTypeName());
            }

            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
