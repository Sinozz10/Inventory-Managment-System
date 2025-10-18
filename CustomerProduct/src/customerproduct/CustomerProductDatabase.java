/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerproduct;

import static java.lang.constant.ConstantDescs.NULL;
import java.util.ArrayList;

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
          
          
          
    
       
       
       
       
       
       
       
       
       
       
       
       }
            
            
