/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author NGUYEN DUC VIET
 */
public class EmployeeService {
    private static EmployeeService instance;
    
    private List<Employee> listEmployee;

    private EmployeeService() {
        listEmployee = new ArrayList<>();
    }
    
    private static EmployeeService getInstance(){
        if (instance == null){
            instance = new EmployeeService();
        }
        return instance;
    }
    
//1. Add employees
//2. Update employees
//3. Remove employees
//4. Search employees
//5. Sort employees by salary
    
    public void addEmployee(){
        
    }
    
    public void updateEmployee(){
        
    }
    
    public void removeEmployee(){
        
    }
    
    public List<Employee> serchEmployeeByName(String partOfName){
        return null;
    }
    
    public void sortEmployeeBySalary(){
        listEmployee.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());
            }
        });
    }
    
    private boolean isDuplicateId(String id){
        for (Employee e : listEmployee) {
            if(e.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    
}
