/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class Fibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int f0 = 0, f1 = 1;
        for (int i = 0; i < 10; i++) {
            System.out.print(f0 + f1 + " ");
            int temp = f0 + f1;
            f0 = f1;
            f1 = temp;
        }
    }
    
}
