# Inventory Management System

A Java-based inventory management system for retail stores with Admin and Employee roles.

## ✨ Features

### 👤 Admin Operations
- Add new employees to the system
- Remove employees from the system
- View all registered employees
- Auto-generated employee IDs

### 👨‍💼 Employee Operations
- Add products to inventory
- Process customer purchases
- Handle product returns (14-day policy)
- Apply payments to purchases
- View all products and purchase history
- Auto-generated product IDs

## 📁 Project Structure

```
├── Main.java                      # System initialization
├── Main1.java                     # Main menu & user interface
├── FilesChecker.java              # File management utility
├── Record.java                    # Interface for all record types
├── Database.java                  # Abstract base class for databases
├── AdminRole.java                 # Admin operations
├── EmployeeRole.java              # Employee operations
├── EmployeeUser.java              # Employee data model
├── EmployeeUserDatabase.java      # Employee database handler
├── Product.java                   # Product data model
├── ProductDatabase.java           # Product database handler
├── CustomerProduct.java           # Purchase transaction model
├── CustomerProductDatabase.java   # Purchase database handler
└── files/
    ├── Employees.txt              # Employee data storage
    ├── Products.txt               # Product data storage
    └── CustomersProducts.txt      # Purchase records storage
```

## 🎯 OOP Concepts Applied (Part 2 - Refactored)

- **Inheritance**: `Database<T>` abstract class extended by all database classes.
- **Polymorphism**: Generic type `<T extends Record>` for flexible database operations.
- **Abstraction**: `Record` interface implemented by all data models.
- **Encapsulation**: Private fields with public getters/setters.

## 💾 Data Storage Format

### Employees.txt
```
E5941,yasin waleed,yasinwaleed@ieee.org.co,Ibrahimeya,+20-1553456712
```

### Products.txt
```
P9828,Laptop,China,Makers,195,780.0
```

### CustomersProducts.txt
```
7830536248,P8921,20-10-2025,false
```

## 🚀 How to Run

1. **Run the main program:**
   ```bash
   java Main
   ```

3. **Navigate through menus:**
   - Choose Admin (1) or Employee (2) operations
   - Follow on-screen prompts
   - Data is automatically saved to files (ALWAYS)

## 🔑 Key Features

### 🎲 Automatic ID Generation
- Employee IDs: E1000 - E9999
- Product IDs: P1000 - P9999
- Customer SSN: 10-digit **random** number

### ↩️ Return Policy
- Customers can return products within 14 days
- Returns update inventory quantities automatically
- Refund amount is returned based on product price

### 💳 Payment Tracking
- Purchases can be marked as paid/unpaid
- Payment status saved in database

### ✅ Input Validation
- Employee names: Letters and spaces only
- Email format: standard email validation
- Phone numbers: Up to 16 characters
- Product IDs: Alphanumeric characters only

## ⚠️ Error Handling

- File not found errors
- Invalid input format validation
- Duplicate ID prevention
- Out of stock validation
- Return date validation

## 📋 Requirements

- No external libraries required

------------------------------
**Course**: CC272 - Programming II  
**Institution**: Alexandria University - Faculty of Engineering  
**Semester**: Fall 2025/2026
