import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUserDatabase {
    private final ArrayList<EmployeeUser> records = new ArrayList<>();
    private final String filename;

    public EmployeeUserDatabase(String filename){
        this.filename = filename;
    }

    public void readFromFile(){
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                EmployeeUser temp = createRecordFrom(reader.nextLine());
                insertRecord(temp);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public EmployeeUser createRecordFrom(String line){
        String[] parts = line.split(",");
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid EmployeeUser object format");
        }
        return new EmployeeUser(parts[0],parts[1],parts[2],parts[3],parts[4]);
    }

    public ArrayList<EmployeeUser> returnAllRecords(){
        return records;
    }

    public boolean contains(String key){
        return getRecord(key) != null;
    }

    public EmployeeUser getRecord(String key){
        for(EmployeeUser emp : records){
            if (key.equals(emp.getSearchKey())){
                return emp;
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record){
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }else {
            throw new IllegalArgumentException("Employee ID must be a unique string");
        }
    }

    public void deleteRecord(String key){
        records.removeIf(emp -> key.equals(emp.getSearchKey()));
    }

    public void saveToFile(){
        StringBuilder text = new StringBuilder();
        for (EmployeeUser emp: records){
            text.append(emp.lineRepresentation());
            text.append("\n");
        }
        try(FileWriter writer = new FileWriter(new File(filename))){
            writer.write(text.toString());
        } catch (IOException e) {
            System.err.println("Error writing to file:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
