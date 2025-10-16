public class EmployeeUser {
    private final String employeeId, name, email, address, phoneNumber;
    public EmployeeUser(String employeeId, String name, String email,
                        String address, String phoneNumber){
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation(){
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }

    public String getSearchKey(){
        return employeeId;
    }
}
