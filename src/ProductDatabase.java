import java.util.regex.Pattern;

public class ProductDatabase extends Database<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }

    //Changed it to override, as I fear any Error
    @Override
    public Product createRecordFrom(String line){
        String[] content =  line.split(",");

        // Validation to only allow 6 inputs
        if (content.length != 6) {throw new IllegalArgumentException("Invalid Product object format");}

        // ID validation to only accept alphanumeric characters
        Pattern pattern1 = Pattern.compile("^[A-Za-z0-9]+$");
        if(!pattern1.matcher(content[0]).find()){throw new IllegalArgumentException("Invalid Product object format");}

        // Name validation to allow words seperated by spaces
        Pattern pattern2 = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)*$");
        if(!pattern2.matcher(content[1]).find()){throw new IllegalArgumentException("Invalid Product object format");}
        if(!pattern2.matcher(content[2]).find()){throw new IllegalArgumentException("Invalid Product object format");}
        if(!pattern2.matcher(content[3]).find()){throw new IllegalArgumentException("Invalid Product object format");}

        try{
            int quantity1= Integer.parseInt(content[4]);
            float price1= Float.parseFloat(content[5]);
            return new Product(content[0],content[1],content[2],content[3],quantity1,price1);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid Product object format");
        }
    }
}
