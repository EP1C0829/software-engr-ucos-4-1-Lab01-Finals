package Lab04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AddProduct {
    private String name;
    private double price;
    private int quantity;

    public AddProduct(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public static void displayFormat()   
    {  
        System.out.format("-----------------------------------------------------------------------");  
        System.out.print("\nName\t\tQuantity\t\tPrice\t\tTotal Price\n");  
        System.out.format("-----------------------------------------------------------------------\n");  
    } 
    
    public void display()   
    {  
        System.out.format("%-9s      %5d                %9.2f     %14.2f\n" ,name, quantity, price, getPrice() * getQuantity());  
    }  
    
}

public class POS {

    public static void main(String[] args) {
        List<AddProduct> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n-------- POINT OF SALES SYSTEM --------");
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Check Out");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Check if the user's input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addProductToCart(scanner, cart);
                        break;
                    case 2:
                        viewCart(cart);
                        break;
                    case 3:
                        checkOutCart(cart);
                        break;
                    case 4:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        scanner.nextLine();
                        break;
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
                choice = 0;
            }
            System.out.println();
        } while (choice != 4);
    }

    private static void addProductToCart(Scanner scanner, List<AddProduct> cart) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        // Convert the product name to lowercase
        name = name.toLowerCase();

        // Check if the product already exists in the cart
        AddProduct existingProduct = null;
        for (AddProduct product : cart) {
            if (product.getName().equalsIgnoreCase(name)) {
                existingProduct = product;
                break;
            }
        }

        if (existingProduct != null) {
            System.out.print("Enter additional quantity: ");
            int additionalQuantity;
            while (true) {
                // Check if the user's input is an integer
                if (scanner.hasNextInt()) {
                    additionalQuantity = scanner.nextInt();
                    if (additionalQuantity >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid quantity. Please enter a non-negative value.");
                    }
                } else {
                    System.out.println("Invalid quantity. Please enter a valid integer value.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            int newQuantity = existingProduct.getQuantity() + additionalQuantity;
            existingProduct.setQuantity(newQuantity);

            System.out.println("Quantity updated for product: " + existingProduct.getName());
        } else {
            double price = 0;
            boolean validPrice = false;
            while (!validPrice) {
                System.out.print("Enter product price: ₱");
                // Check if the user's input is a double
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price >= 0) {
                        validPrice = true;
                    } else {
                        System.out.println("Invalid price. Please enter a non-negative value.");
                    }
                } else {
                    System.out.println("Invalid price. Please enter a valid numeric value.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            int quantity = 0;
            boolean validQuantity = false;
            while (!validQuantity) {
                System.out.print("Enter product quantity: ");
                // Check if the user's input is an integer
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                    if (quantity >= 0) {
                        validQuantity = true;
                    } else {
                        System.out.println("Invalid quantity. Please enter a non-negative value.");
                    }
                } else {
                    System.out.println("Invalid quantity. Please enter a valid integer value.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            AddProduct product = new AddProduct(name, price, quantity);
            cart.add(product);

            System.out.println("Product added to cart.");
        }
    }

    private static void viewCart(List<AddProduct> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("-------- CART --------");
            int productNumber = 1;
            for (AddProduct product : cart) {
            	System.out.println("Product " + productNumber);
                System.out.println("Name: " + product.getName());
                System.out.println("Price: ₱" + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Total: ₱" + (product.getPrice() * product.getQuantity()));
                System.out.println("----------------------");
                productNumber++;
            }
        }
    	
    	
    }

    private static void checkOutCart(List<AddProduct> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to check out.");
        } else {
        	AddProduct.displayFormat();
            double totalAmount = 0;
            int productNumber = 1;
            for (AddProduct product : cart) {
                double productTotal = product.getPrice() * product.getQuantity();
                totalAmount += productTotal;
                productNumber++;
                product.display();
            }
            System.out.format("-----------------------------------------------------------------------");  
            System.out.println("\nTotal Amount: ₱" + totalAmount);
            cart.clear();
            System.out.println("Cart cleared. Thank you for your purchase!");
        }
    }
 }