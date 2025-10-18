/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public class CustomerProduct {
    private String customerSSN;
    private String productID; 
    private LocalDate purchaseDate;
    private boolean paid; 

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
    return customerSSN + "," + productID + "," + purchaseDate + "," + paid ; 
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public String getSearchKey(){
     return customerSSN + "," + productID + "," + purchaseDate;
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        System.out.println("wishy");
    }
    
}
