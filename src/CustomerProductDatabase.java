import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class CustomerProductDatabase extends Database<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] product = line.split(",");

        // ID validation to only accept alphanumeric characters
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if(!pattern.matcher(product[1]).find()){throw new IllegalArgumentException("Invalid CustomerProduct object format");}

        if (product[0].length() != 10){throw new IllegalArgumentException("Invalid CustomerProduct object format");}

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(product[2], formatter);
        CustomerProduct cust = new CustomerProduct(product[0], product[1], date);

        try{
            Long.parseLong(product[0]);
            return cust;
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid CustomerProduct object format");
        }
    }
}
            
            
