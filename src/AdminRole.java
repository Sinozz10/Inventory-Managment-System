/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HY
 */
public class AdminRole {
    private EmployeeUserDatabase database;
    
    public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser employeeToAdd = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(employeeToAdd);
        database.saveToFile();
    }
    
    
    public void removeEmployee(String ID_key) {
        if (database.contains(ID_key)) {
            database.deleteRecord(ID_key);
            database.saveToFile();
        }
        }
    public EmployeeUser[] getListOfEmployees() {
        
        return database.returnAllRecords().toArray(new EmployeeUser[database.returnAllRecords().size()]);

    }
    public void logout() {
        database.saveToFile();
        System.out.println("Logged Out");
    }

}
