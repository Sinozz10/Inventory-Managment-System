/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerproduct;

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
            
            
            
       }
            
            }
