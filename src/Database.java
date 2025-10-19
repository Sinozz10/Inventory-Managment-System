import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database<T extends Record> {
    private final ArrayList<T> records = new ArrayList<>();
    private final String filename;

    // init function, also calls readFromFile so that data is automatically loaded on creation
    public Database(String filename){
        this.filename = filename;
        readFromFile();
    }

    // file reading and writing
    public void readFromFile(){
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                T temp = createRecordFrom(reader.nextLine());
                insertRecord(temp);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Warning: file not found -> " + filename + ", starting with empty record table." +
                    " Create file or writing will fail.");
        }
    }

    public void saveToFile(){
        StringBuilder text = new StringBuilder();
        for (T emp: records){
            text.append(emp.lineRepresentation());
            text.append("\n");
        }
        try(FileWriter writer = new FileWriter(filename)){
            writer.write(text.toString());
        } catch (IOException e) {
            System.err.println("Error writing to file:" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // record creation, deletion and searching
    public abstract T createRecordFrom(String line);

    public void insertRecord(T record){
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }else {
            throw new IllegalArgumentException("Record ID must be a unique string.");
        }
    }

    public void insertNewRecord(String line){
        insertRecord(createRecordFrom(line));
    }

    public void deleteRecord(String key){
        T temp = getRecord(key);
        if (temp != null){
            records.remove(temp);
        }else {
            throw new IllegalArgumentException("Given record ID does not exist in records.");
        }
    }

    public T getRecord(String key){
        for(T emp : records){
            if (key.equals(emp.getSearchKey())){
                return emp;
            }
        }
        return null;
    }

    public boolean contains(String key){
        return getRecord(key) != null;
    }

    // getters
    public ArrayList<T> returnAllRecords(){
        return new ArrayList<>(records);
    }

    public String getFilename(){
        return filename;
    }
}
