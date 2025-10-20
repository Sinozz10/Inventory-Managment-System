import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ReferenceMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static AdminRole admin;
    private static EmployeeRole employee;

    public static void main(String[] args) {
        System.out.println("""
        ========================================
          INVENTORY MANAGEMENT SYSTEM
        ========================================
        """);

        // Initialize roles
        admin = new AdminRole();
        employee = new EmployeeRole();

        boolean running = true;

        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Admin Operations");
            System.out.println("2. Employee Operations");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 3:
                    System.out.println("\nExiting system...");
                    admin.logout();
                    employee.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } while (running);

        scanner.close();
        System.out.println("System closed successfully!");
    }

    /**
     * Admin Operations Menu
     */

    private static void adminMenu() {
        boolean adminRunning = true;

        do {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Logout (Save and Return)");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    admin.logout();
                    System.out.println("Admin logged out!");
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            if (adminRunning) {
                System.out.print("\nContinue as Admin? (y/n): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("y")) {
                    admin.logout();
                    adminRunning = false;
                }
            }

        } while (adminRunning);
    }

    /**
     * Employee Operations Menu
     */

    private static void employeeMenu() {
        boolean employeeRunning = true;

        do {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Purchase Product (Customer Buy)");
            System.out.println("4. Return Product");
            System.out.println("5. Apply Payment");
            System.out.println("6. View All Purchases");
            System.out.println("7. Logout (Save and Return)");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    purchaseProduct();
                    break;
                case 4:
                    returnProduct();
                    break;
                case 5:
                    applyPayment();
                    break;
                case 6:
                    viewAllPurchases();
                    break;
                case 7:
                    employee.logout();
                    System.out.println("Employee logged out!");
                    employeeRunning = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            if (employeeRunning) {
                System.out.print("\nContinue as Employee? (y/n): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("y")) {
                    employee.logout();
                    employeeRunning = false;
                }
            }

        } while (employeeRunning);
    }

    // ==================== ADMIN OPERATIONS ====================

    private static void addEmployee() {
        System.out.println("\n--- Add Employee ---");

        String id = "E" + generatenum();
        System.out.println("Employee ID: " + id);

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Phone Number: +20-");
        String phone = "+20-" + scanner.nextLine().trim();

        admin.addEmployee(id, name, email, address, phone);
        System.out.println("✓ Employee added successfully!");
    }

    private static void removeEmployee() {
        System.out.println("\n--- Remove Employee ---");

        System.out.print("Employee ID to remove: ");
        String id = scanner.nextLine().trim();

        admin.removeEmployee(id);
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- All Employees ---");
        EmployeeUser[] employees = admin.getListOfEmployees();

        if (employees.length == 0) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-12s | %-20s | %-25s | %-15s%n",
                "ID", "Name", "Email", "Phone");
        System.out.println("=".repeat(80));

        for (EmployeeUser emp : employees) {
            System.out.printf("%-12s | %-20s | %-25s | %-15s%n",
                    emp.getSearchKey(), emp.getName(), emp.getEmail(), emp.getPhoneNumber());
        }
        System.out.println("=".repeat(80));
        System.out.println("Total employees: " + employees.length);
    }

    // ==================== EMPLOYEE OPERATIONS ====================

    private static void addProduct() {
        System.out.println("\n--- Add Product ---");

        String id = "P" + generatenum();
        System.out.println("Product ID: " + id);

        System.out.print("Product Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Manufacturer Name: ");
        String manufacturer = scanner.nextLine().trim();

        System.out.print("Supplier Name: ");
        String supplier = scanner.nextLine().trim();

        System.out.print("Quantity: ");
        int quantity = getIntInput();

        System.out.print("Price: ");
        int price = getIntInput();

        employee.addProduct(id, name, manufacturer, supplier, quantity, price);
        System.out.println("✓ Product added successfully!");
    }

    private static void viewAllProducts() {
        System.out.println("\n--- All Products ---");

        if (employee.getListOfProducts().length == 0) {
            System.out.println("No products found!");
            return;
        }

        System.out.println("\n" + "=".repeat(90));
        System.out.printf("%-12s | %-15s | %-15s | %-15s | %-10s%n",
                "Product ID", "Name", "Manufacturer", "Supplier", "Quantity");
        System.out.println("=".repeat(90));

        for (Product p : employee.getListOfProducts()) {
            System.out.printf("%-12s | %-15s | %-15s | %-15s | %-10d%n",
                    p.getProductID(), p.getProductName(), p.getManufacturerName(),
                    p.getSupplierName(), p.getQuantity());
        }
        System.out.println("=".repeat(90));
        System.out.println("Total products: " + employee.getListOfProducts().length);
    }

    private static void purchaseProduct() {
        System.out.println("\n--- Purchase Product ---");

        String ssn = generateSSN();
        System.out.println("Customer SSN: " + ssn);

        System.out.print("Product ID: ");
        String productId = scanner.nextLine().trim();

        System.out.print("Purchase Date (DD-MM-YYYY) or press Enter for today: ");
        String dateStr = scanner.nextLine().trim();

        LocalDate purchaseDate;
        if (dateStr.isEmpty()) {
            purchaseDate = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                purchaseDate = LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date format! Using today's date.");
                purchaseDate = LocalDate.now();
            }
        }

        boolean success = employee.purchaseProduct(ssn, productId, purchaseDate);

        if (success) {
            System.out.println("✓ Purchase completed successfully!");
            Product product = employee.GetProductDatabase().getRecord(productId);
            if (product != null) {
                System.out.println("Remaining quantity: " + product.getQuantity());
            }
        } else {
            System.out.println("✗ Purchase failed! Product may be out of stock or doesn't exist.");
        }
    }

    private static void returnProduct() {
        System.out.println("\n--- Return Product ---");

        System.out.print("Customer SSN: ");
        String ssn = scanner.nextLine().trim();

        System.out.print("Product ID: ");
        String productId = scanner.nextLine().trim();

        System.out.print("Purchase Date (DD-MM-YYYY): ");
        String purchaseDateStr = scanner.nextLine().trim();

        System.out.print("Return Date (DD-MM-YYYY) or press Enter for today: ");
        String returnDateStr = scanner.nextLine().trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate purchaseDate = LocalDate.parse(purchaseDateStr, formatter);
            LocalDate returnDate;

            if (returnDateStr.isEmpty()) {
                returnDate = LocalDate.now();
            } else {
                returnDate = LocalDate.parse(returnDateStr, formatter);
            }

            double refund = employee.returnProduct(ssn, productId, purchaseDate, returnDate);

            if (refund == -1) {
                System.out.println("✗ Return failed!");
                System.out.println("Possible reasons:");
                System.out.println("  - Return date is before purchase date");
                System.out.println("  - More than 14 days have passed");
                System.out.println("  - Product doesn't exist");
                System.out.println("  - Purchase record not found");
            } else {
                System.out.println("✓ Return successful!");
                System.out.println("Refund amount: $" + refund);
                Product product = employee.GetProductDatabase().getRecord(productId);
                if (product != null) {
                    System.out.println("Updated quantity: " + product.getQuantity());
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid date format!");
        }
    }

    private static void applyPayment() {
        System.out.println("\n--- Apply Payment ---");

        System.out.print("Customer SSN: ");
        String ssn = scanner.nextLine().trim();

        System.out.print("Purchase Date (DD-MM-YYYY): ");
        String dateStr = scanner.nextLine().trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate purchaseDate = LocalDate.parse(dateStr, formatter);
            boolean success = employee.applyPayment(ssn, purchaseDate);

            if (success) {
                System.out.println("✓ Payment applied successfully!");
            } else {
                System.out.println("✗ Payment failed! Purchase not found or already paid.");
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid date format!");
        }
    }

    private static void viewAllPurchases() {
        System.out.println("\n--- All Purchase Operations ---");

        if (employee.getListOfPurchasingOperations().length == 0) {
            System.out.println("No purchases found!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.printf("%-15s | %-12s | %-15s | %-10s%n",
                "Customer SSN", "Product ID", "Purchase Date", "Paid");
        System.out.println("=".repeat(80));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (CustomerProduct cp : employee.getListOfPurchasingOperations()) {
            System.out.printf("%-15s | %-12s | %-15s | %-10s%n",
                    cp.getCustomerSSN(), cp.getProductID(),
                    cp.getPurchaseDate().format(formatter),
                    cp.isPaid() ? "Yes" : "No");
        }
        System.out.println("=".repeat(80));
        System.out.println("Total purchases: " + employee.getListOfPurchasingOperations().length);
    }

    //Extra Methods added -Yassin

    private static int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private static String generatenum() {
        int random = 1000 + (int) (Math.random() * 9000);
        return String.valueOf(random);
    }

    private static String generateSSN() {
        int random1 = 10000 + (int) (Math.random() * 90000);
        int random2 = 10000 + (int) (Math.random() * 90000);
        return String.valueOf(random1) + random2;
    }
}