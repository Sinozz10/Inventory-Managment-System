/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class CustomerProductDatabase {
    private ArrayList<CustomerProduct> records;
    private String filename;
    
    public CustomerProductDatabase(String filename) {
        this.filename = filename;
    }
    
            
     public ArrayList<CustomerProduct> returnAllRecords(){
         return records;
     }       
            
       public boolean contains(String key){
       
          for(int i=0;i<records.size();i++){
             if(records.get(i).getSearchKey().equals(key)) {
              return true;}
       }   
          
         return false;         
       }    
            
    /**
     *
     * @param record
     */
    public void insertRecord(CustomerProduct record){
          if(!contains(record.getSearchKey())) {
            records.add(record); }   
          else {
              System.out.println("already exists"); 
          }
       }     
       
           
       public void deleteRecord(String key){
            if(!contains(key)){
          System.out.println("this customer product object doesnt exist");     
          }
            else{
           for(int i=0;i<records.size();i++){
             if(records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                  break;}}
             } }   
                  
       
       public CustomerProduct getRecord(String key){
          if(!contains(key)){
          System.out.println("this customer product object doesnt exist"); 
          }
             else  {
               for(int i=0;i<records.size();i++){
             if(records.get(i).getSearchKey().equals(key)) {
              return records.get(i);}}}    
          return null;
          }
          
      public CustomerProduct createRecordFrom(String line){    
        String[] product=line.split(",");
          boolean p=Boolean.parseBoolean(product[3]);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          LocalDate date=LocalDate.parse(product[2], formatter);    
        CustomerProduct cust =new CustomerProduct(product[0],product[1],date);      
        cust.setPaid(p);
       return cust;         
                }
          
    
      public void readFromFile() throws FileNotFoundException{
     File f=new File(filename);
     Scanner scan=new Scanner(f);
      while(scan.hasNextLine()){
       CustomerProduct cust= createRecordFrom(scan.nextLine());
        insertRecord(cust);
      }
   }  
      
      
      
        public void saveToFile(){
        
        
        
       
        }   
       
       
       
       
       
       
       
       
       
       
       }
            
            
