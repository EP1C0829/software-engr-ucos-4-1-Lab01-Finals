package Lab04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AddProduct {
    private String name;
    private double price;
    private int quantity;
    private static double totalPrice;

    public AddProduct(String name, double price, int quantity, double totalPrice) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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
    
    public double getTotalPrice()   
    {  
        return totalPrice;  
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


	                //displayFormat  
	                public static void displayFormat()   
	                {  
	                	System.out.println("\n-------- POINT OF SALES SYSTEM --------");
	                    System.out.format("-----------------------------------------------------------------------");  
	                    System.out.print("\nName\t\tQuantity\t\tPrice\t\tTotal Price\n");  
	                    System.out.format("-----------------------------------------------------------------------\n");  
	                }  
	                   
	                // display  
	                public void display()   
	                {  
	                	double totalPrice = price * quantity; 
	                    System.out.format("%-9s      %5d                %9.2f     %14.2f\n" ,name, quantity, price, totalPrice);
	                }  

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

    double price = 0;
    boolean validPrice = false;
    while (!validPrice) {
        System.out.print("Enter product price: ₱");
        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
            validPrice = true;
        } else {
            System.out.println("Invalid price. Please enter a valid numeric value.");
            scanner.nextLine(); 
        }
    }

    int quantity = 0;
    boolean validQuantity = false;
    while (!validQuantity) {
        System.out.print("Enter product quantity: ");
        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
            validQuantity = true;
        } else {
            System.out.println("Invalid quantity. Please enter a valid integer value.");
            scanner.nextLine(); 
            scanner.nextLine(); 
        }
    }

    AddProduct product = new AddProduct(name, price, quantity, totalPrice);
    cart.add(product);
    System.out.println("Product added to cart.");
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
	                //display all product with its properties  
	                AddProduct.displayFormat();  
	                double checkoutAmount = 0;
					for (AddProduct product : cart)   
	                {  
	                    product.display();  
	                    checkoutAmount += product.price * product.quantity;
	                }  
                    System.out.format("-----------------------------------------------------------------------");  
                    System.out.println("\nTotal Amount: " + checkoutAmount);
                    System.out.println("Thank you for your purchase!");
                    cart.clear();
	            }     
	    }  
}