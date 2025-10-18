public class EmployeeUser {
    // all variables are final
    private final String employeeId, name, email, address, phoneNumber;

    // init function
    public EmployeeUser(String employeeId, String name, String email,
                        String address, String phoneNumber){
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // returns a serialized representation of the employee object
    public String lineRepresentation(){
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }

    public String getSearchKey(){
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
