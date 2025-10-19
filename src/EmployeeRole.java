import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    private boolean paymentFlag = false;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase(FilesChecker.getProductPath());
        customerProductDatabase = new CustomerProductDatabase(FilesChecker.getCustomerProductPath());
    }

    public void SetProductDatabase(ProductDatabase A_productDatabase) {
        this.productsDatabase = A_productDatabase;
    }

    public ProductDatabase GetProductDatabase() {
        return productsDatabase;
    }

    public void SetCustomerProductDatabase(CustomerProductDatabase A_CustomerProductDatabase) {
        this.customerProductDatabase = A_CustomerProductDatabase;
    }
    
    public CustomerProductDatabase GetCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        productsDatabase.insertNewRecord(productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price);
        productsDatabase.saveToFile();
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public Product[] getListOfProducts() {
        return productsDatabase.returnAllRecords().toArray(new Product[productsDatabase.returnAllRecords().size()]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product productToCheck = productsDatabase.getRecord(productID);
        if (productToCheck != null && productToCheck.getQuantity() != 0) {

            productToCheck.setQuantity(productToCheck.getQuantity() - 1);
            productsDatabase.saveToFile();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            customerProductDatabase.insertNewRecord(customerSSN + "," + productID + "," + purchaseDate.format(formatter));
            customerProductDatabase.saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {

        Product productToReturn = productsDatabase.getRecord(productID);

        if (returnDate.isBefore(purchaseDate) || (ChronoUnit.DAYS.between(purchaseDate, returnDate)) > 14) {
            return -1;
        }

        if (productToReturn == null) {
            return -1;
        }

        if (!customerProductDatabase.contains(customerSSN + "," + productID + "," + String.format("%02d-%02d-%04d", purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear()))) {
            return -1;
        }

        productToReturn.setQuantity(productToReturn.getQuantity() + 1);
        productsDatabase.saveToFile();

        customerProductDatabase.deleteRecord(customerSSN + "," + productID + "," + String.format("%02d-%02d-%04d", purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear()));
        customerProductDatabase.saveToFile();

        return productToReturn.getPrice();
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("All unsaved data,Are saved now.");
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        paymentFlag = false; //3ashan kol mara y5osh yzabat el flag.
        for (int i = 0; i < customerProductDatabase.returnAllRecords().size(); i++) {
            CustomerProduct record = customerProductDatabase.returnAllRecords().get(i);

            if (record.getCustomerSSN().equals(customerSSN) && record.getPurchaseDate().equals(purchaseDate)) {
                if (!record.isPaid()) {
                    record.setPaid(true);
                    customerProductDatabase.saveToFile();
                    paymentFlag = true;
                }
            }
        }
        return paymentFlag;
    }
}
