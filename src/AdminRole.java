public class AdminRole {
    private EmployeeUserDatabase database;
    
    public AdminRole() {
        database = new EmployeeUserDatabase(FilesChecker.getEmployeePath());
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        database.insertNewRecord(employeeId + "," + name + "," + email + "," + address + "," + phoneNumber);
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
