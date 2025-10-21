import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record{
    private final String customerSSN, productID;
    private final LocalDate purchaseDate;
    private boolean paid;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

     public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String lineRepresentation(){
    return customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid ;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String getSearchKey(){
     return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
    }
}