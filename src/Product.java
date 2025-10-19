import java.util.*;
import java.io.*;

public class Product {
    //Init
    //Start
    //1 Constructor DONE
    //4 Methods DONE

    //Attributes
    //I Made them Final as they won't change anymore after getting.
    private final String productID, productName, manufacturerName, supplierName;
    private int quantity ; //Except Quantity.
    private final float price;

    //Constructor
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID=productID;
        this.productName=productName;
        this.manufacturerName=manufacturerName;
        this.supplierName=supplierName;
        this.quantity=quantity;
        this.price=price;
    }

    //Methods
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0){
            this.quantity=quantity;
        }else{
            this.quantity=0;
        }
    }

    public String getSearchKey(){
        return this.productID;
    }

    public String lineRepresentation(){
        return this.productID + "," + this.productName + "," + this.manufacturerName + "," + this.supplierName + "," + this.quantity + "," + this.price;
    }


    // Getters
    //Doesn't have Setters as they are final.

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public float getPrice() {
        return price;
    }

}
