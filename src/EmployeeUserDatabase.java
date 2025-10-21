import java.util.regex.Pattern;

public class EmployeeUserDatabase extends Database<EmployeeUser>{
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line){
        String[] parts = line.split(",");

        // validation to only allow 5 inputs
        if (parts.length != 5) {throw new IllegalArgumentException("Invalid EmployeeUser object format");}

        // phone numbar validation
        if(parts[4].length() > 16){throw new IllegalArgumentException("Invalid EmployeeUser object format");}

        // ID validation to only accept alphanumeric characters
        Pattern pattern1 = Pattern.compile("^[A-Za-z0-9]+$");
        if(!pattern1.matcher(parts[0].trim()).find()){throw new IllegalArgumentException("Invalid EmployeeUser object format");}

        // Name validation to allow words seperated by spaces
        Pattern pattern2 = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)*$");
        if(!pattern2.matcher(parts[1].trim()).find()){throw new IllegalArgumentException("Invalid EmployeeUser object format");}

        // email validation to only allow the pattern word/-/1-9@word/-/1-9.word (more than 2 characters)
        Pattern pattern3 = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
        if(!pattern3.matcher(parts[2].trim()).find()){throw new IllegalArgumentException("Invalid EmployeeUser object format");}

        try{
            return new EmployeeUser(parts[0],parts[1],parts[2],parts[3],parts[4]);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid EmployeeUser object format");
        }
    }
}
