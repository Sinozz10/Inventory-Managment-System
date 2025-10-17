import java.util.*;

public class ProductDatabase {
    //ProductDatabase
    // (Constructors: 1 Methods: 8)

    //Attributes
    private ArrayList<Product> records;
    private final String filename;

    //Constructor
    public ProductDatabase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }


}
