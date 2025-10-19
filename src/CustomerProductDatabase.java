import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] product = line.split(",");
        boolean p = Boolean.parseBoolean(product[3]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(product[2], formatter);
        CustomerProduct cust = new CustomerProduct(product[0], product[1], date);
        cust.setPaid(p);
        return cust;
    }
}
            
            
