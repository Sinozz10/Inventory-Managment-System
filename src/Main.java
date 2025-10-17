public void filesChecker(){
    Path directory = Paths.get("files");
    try{
        if (Files.notExists(directory)) {Files.createDirectory(directory);}
        File e = new File("files/Employees.txt"), p = new File("files/Products.txt"),
                c = new File("files/CustomersProducts.txt");
        e.createNewFile();
        p.createNewFile();
        c.createNewFile();

    }catch (IOException e){
        System.out.println("Something went wrong with creating the folder: " + e.getMessage());
        e.printStackTrace();
    }
}

void main() {
    filesChecker();
}
