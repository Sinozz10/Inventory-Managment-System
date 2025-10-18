import java.io.*;
import java.util.*;

public class ProductDatabase {
    //ProductDatabase
    //Constructors: 1 DONE
    //Methods: 8 DONE

    //Attributes
    private ArrayList<Product> records;
    private final String filename;

    //Constructor
    public ProductDatabase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>();

        readFromFile();
    }

    //Methods
    public void readFromFile() {
        File f = new File("Products.txt");
        System.out.println("File exists: " + f.exists());
        System.out.println("Absolute path: " + f.getAbsolutePath());

        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                Product product1 = createRecordFrom(line);
                if (product1 != null) {
                    records.add(product1);
                }
            }
        }catch (IOException error){
            System.out.println("Error reading file : " + filename);

        }
    }

    public Product createRecordFrom(String line){
        String[] content =  line.split(",");
        if (content[0].isEmpty()) {
            System.out.println("No Data in File");
            return null;
        }else
        {
            int quantity1= Integer.parseInt(content[4]);
            float price1= Float.parseFloat(content[5]);
            return new Product(content[0],content[1],content[2],content[3],quantity1,price1);
        }
    }


    public ArrayList<Product> returnAllRecords(){
        return this.records;
    }

    //khalebalko contains and getRecord are different
    //Returns True or False, if it's there wla la
    public boolean contains(String key){
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                return true;
            }
        }
        return false; //Not Found.
    }

    //dah by-get the exact PRODUCT
    public Product getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                return records.get(i);
            }
        }
        return null;
    }


    public void insertRecord(Product record){
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }else {
            System.out.println("Record already exists, ID must be unique !!!!");
        }
    }

    public void deleteRecord(String key){
        Product record2 = getRecord(key);
        if (record2 != null) {
            records.remove(record2);
        }else{
            System.out.println("No Such Record Exists..... , Please Try Again with a valid Record to be deleted");
        }
    }

    public void saveToFile(){
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename)) ){
            for (int i = 0; i < records.size(); i++) {
                pw.println(records.get(i).lineRepresentation());

            }
        }catch (IOException error){
            System.out.println("Error saving file : " + filename);
        }

    }

    //Getters and setters

    public ArrayList<Product> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Product> records) {
        this.records = records;
    }

    public String getFilename() {
        return filename;
    }
}
