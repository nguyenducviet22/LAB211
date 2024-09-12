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
import model.Product;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class ProductRepository extends HashMap<String, Product> implements ICrud<String, Product> {

    @Override
    public int create(Product newItem) {
        try {
            this.put(newItem.getId(), newItem);
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public HashMap<String, Product> read() {
        return this;
    }

    @Override
    public Product details(String id) {
        return this.get(id);
    }

    @Override
    public int update(Product editItem) {
        try {
            Product oldItem = this.get(editItem.getId());
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
                String _brand_id = stk.nextToken();
                String _cate_id = stk.nextToken();
                String _model = stk.nextToken();
                String _price = stk.nextToken();

                // Tái tạo Java Object
                Product _br = new Product(_id, _name, _brand_id, _cate_id, Integer.parseInt(_model), Double.parseDouble(_price));

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

            for (Map.Entry<String, Product> entry : this.entrySet()) {
                System.out.println();
                Product item = entry.getValue();
                pw.println(item.getId() + "," + item.getName() + "," + item.getBrand_id() + "," + item.getCategory_id() + "," + item.getModel_year() + "," + item.getList_price());
            }

            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
