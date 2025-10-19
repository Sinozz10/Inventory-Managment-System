import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesChecker {
    private static final String employee = "files/Employees.txt", product = "files/Products.txt", customer = "files/CustomersProducts.txt";

    public static void filesChecker(){
        Path directory = Paths.get("files");
        try{
            if (Files.notExists(directory)) {Files.createDirectory(directory);}
            File e = new File(employee), p = new File(product), c = new File(customer);
            e.createNewFile();
            p.createNewFile();
            c.createNewFile();

        }catch (IOException e){
            System.out.println("Something went wrong with creating the folder: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getEmployeePath(){
        return employee;
    }

    public static String getProductPath(){
        return product;
    }

    public static String getCustomerProductPath(){
        return customer;
    }
}
