import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        productsDatabase.readFromFile();

        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        customerProductDatabase.readFromFile();
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

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        float price1=0;
        Product productAdded = new Product(productID,productName,manufacturerName,supplierName,quantity,price1);
        productsDatabase.insertRecord(productAdded);
        productsDatabase.saveToFile();
    }

    public ArrayList<Product> getListOfProducts() {
        return productsDatabase.returnAllRecords();
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.readFromFile();
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product productToCheck = productsDatabase.getRecord(productID);
        if (productToCheck != null && productToCheck.getQuantity() != 0) {

            productToCheck.setQuantity(productToCheck.getQuantity() - 1);
            productsDatabase.saveToFile();
            CustomerProduct createPurchase = new CustomerProduct(customerSSN,productID, purchaseDate);
            customerProductDatabase.insertRecord(createPurchase);
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
        String formattedDate = String.format("%02d-%02d-%04d", purchaseDate.getDayOfMonth(), purchaseDate.getMonthValue(), purchaseDate.getYear());

        CustomerProduct transaction = customerProductDatabase.getRecord(customerSSN + ",," + formattedDate);

        if (transaction == null || transaction.isPaid()) {
            return false;
        } else {
            transaction.setPaid(true);
            customerProductDatabase.saveToFile();
            return true;
        }
    }

}
