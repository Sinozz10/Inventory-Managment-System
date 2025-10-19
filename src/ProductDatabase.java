public class ProductDatabase extends Database<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }

    public Product createRecordFrom(String line){
        String[] content =  line.split(",");
        if (content[0].isEmpty()) {
            System.out.println("No Data in File");
            return null;
        }else
        {
            int quantity1= Integer.parseInt(content[4]);
            float price1= Float.parseFloat(content[5]);
            return new Product(content[0],content[1],content[2],content[3],quantity1,price1);
        }
    }
}
