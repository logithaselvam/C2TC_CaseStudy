package com.fooddelivery;
import java.util.Scanner;

	public class Main {
	    private static RestaurantService restaurantService = new RestaurantService();
	    private static CustomerService customerService = new CustomerService();
	    private static OrderService orderService = new OrderService();
	    private static java.util.List<DeliveryPerson> deliveryPeople = new java.util.ArrayList<>();

	    public static void main(String[] args) {
	        seedData();
	        Scanner sc = new Scanner(System.in);
	        while (true) {
	            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit\nChoose option:");
	            int opt = Integer.parseInt(sc.nextLine());
	            if (opt == 1) adminMenu(sc);
	            else if (opt == 2) customerMenu(sc);
	            else { System.out.println("Exiting. Bye!"); break; }
	        }
	        sc.close();
	    }

	    private static void seedData() {
	        Restaurant r1 = new Restaurant(101, "HariOmDhaba");
	        r1.addFoodItem(new FoodItem(1, "PanjabiThali", 340.0));
	        r1.addFoodItem(new FoodItem(2, "PavBhaji", 140.0));
	        restaurantService.addRestaurant(r1);

	        Restaurant r2 = new Restaurant(102, "ExpressInn");
	        restaurantService.addRestaurant(r2);
	    }

	    private static void adminMenu(Scanner sc) {
	        while (true) {
	            System.out.println("\nAdmin Menu:\n1.Add Restaurant\n2.Add Food Item to Restaurant\n3.Remove Food Item from Restaurant\n4.View Restaurants and Menus\n5.View Orders\n6.Add Delivery Person\n7.Assign Delivery Person to Order\n8.Exit\nChoose option:");
	            int a = Integer.parseInt(sc.nextLine());
	            switch (a) {
	                case 1:
	                    System.out.print("Enter Restaurant ID: "); int rid = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Restaurant Name: "); String rname = sc.nextLine();
	                    restaurantService.addRestaurant(new Restaurant(rid, rname));
	                    System.out.println("Restaurant added successfully!");
	                    break;
	                case 2:
	                    System.out.print("Enter Restaurant ID: ");
	                    int rId = Integer.parseInt(sc.nextLine());
	                    restaurantService.findById(rId).ifPresentOrElse(r -> {
	                        try {
	                            System.out.print("Enter Food Item ID: "); int fid = Integer.parseInt(sc.nextLine());
	                            System.out.print("Enter Food Item Name: "); String fname = sc.nextLine();
	                            System.out.print("Enter Food Item Price: "); double fprice = Double.parseDouble(sc.nextLine());
	                            r.addFoodItem(new FoodItem(fid, fname, fprice));
	                            System.out.println("Food item added successfully!");
	                        } catch (Exception e) { System.out.println("Invalid input."); }
	                    }, () -> System.out.println("Restaurant not found."));
	                    break;
	                case 3:
	                    System.out.print("Enter Restaurant ID: ");
	                    int rr = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Food Item ID to remove: "); int fidr = Integer.parseInt(sc.nextLine());
	                    restaurantService.findById(rr).ifPresentOrElse(r -> {
	                        r.removeFoodItemById(fidr);
	                        System.out.println("Food removed if existed.");
	                    }, () -> System.out.println("Restaurant not found."));
	                    break;
	                case 4:
	                    System.out.println("Restaurants and Menus:");
	                    restaurantService.getAll().forEach(r -> {
	                        System.out.println(r);
	                        r.getMenu().forEach(fi -> System.out.println(" - " + fi));
	                    });
	                    break;
	                case 5:
	                    System.out.println("Orders:");
	                    orderService.getAllOrders().forEach(System.out::println);
	                    break;
	                case 6:
	                    System.out.print("Enter Delivery Person ID: "); int dpId = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Delivery Person Name: "); String dpName = sc.nextLine();
	                    System.out.print("Enter Contact No.: "); long dpContact = Long.parseLong(sc.nextLine());
	                    deliveryPeople.add(new DeliveryPerson(dpId, dpName, dpContact));
	                    System.out.println("Delivery person added successfully!");
	                    break;
	                case 7:
	                    System.out.print("Enter Order ID: "); int oid = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Delivery Person ID: "); int did = Integer.parseInt(sc.nextLine());
	                    orderService.findById(oid).ifPresentOrElse(order -> {
	                        deliveryPeople.stream().filter(d -> d.getDeliveryPersonId() == did).findFirst().ifPresentOrElse(dp -> {
	                            order.setDeliveryPerson(dp);
	                            System.out.println("Delivery person assigned!");
	                        }, () -> System.out.println("Delivery person not found."));
	                    }, () -> System.out.println("Order not found."));
	                    break;
	                case 8: System.out.println("Exiting Admin Module"); return;
	                default: System.out.println("Invalid option"); break;
	            }
	        }
	    }

	    private static void customerMenu(Scanner sc) {
	        while (true) {
	            System.out.println("\nCustomer Menu:\n1.Add Customer\n2.View Food Items\n3.Add Food to Cart\n4.View Cart\n5.Place Order\n6.View Orders\n7.Exit\nChoose option:");
	            int c = Integer.parseInt(sc.nextLine());
	            switch (c) {
	                case 1:
	                    System.out.print("Enter User ID: "); int uid = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Username: "); String uname = sc.nextLine();
	                    System.out.print("Enter Contact No.: "); long ucontact = Long.parseLong(sc.nextLine());
	                    customerService.addCustomer(new Customer(uid, uname, ucontact));
	                    System.out.println("Customer created successfully!");
	                    break;
	                case 2:
	                    System.out.println("Restaurants and Menus:");
	                    restaurantService.getAll().forEach(r -> {
	                        System.out.println(r);
	                        r.getMenu().forEach(fi -> System.out.println(" - " + fi));
	                    });
	                    break;
	                case 3:
	                    System.out.print("Enter Customer ID: "); int custId = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Restaurant ID: "); int restId = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Food Item ID: "); int foodId = Integer.parseInt(sc.nextLine());
	                    System.out.print("Enter Quantity: "); int qty = Integer.parseInt(sc.nextLine());
	                    var custOpt = customerService.findById(custId);
	                    var restOpt = restaurantService.findById(restId);
	                    if (custOpt.isPresent() && restOpt.isPresent()) {
	                        Customer cust = custOpt.get();
	                        Restaurant rest = restOpt.get();
	                        rest.getMenu().stream().filter(fi -> fi.getId() == foodId).findFirst().ifPresentOrElse(fi -> {
	                            cust.getCart().addItem(fi, qty);
	                            System.out.println("Food item added to cart!");
	                        }, () -> System.out.println("Food item not found."));
	                    } else System.out.println("Customer or Restaurant not found.");
	                    break;
	                case 4:
	                    System.out.print("Enter Customer ID: "); int cid = Integer.parseInt(sc.nextLine());
	                    customerService.findById(cid).ifPresentOrElse(cust -> {
	                        System.out.println("Cart:\n" + cust.getCart());
	                    }, () -> System.out.println("Customer not found."));
	                    break;
	                case 5:
	                    System.out.print("Enter Customer ID: "); int cId = Integer.parseInt(sc.nextLine());
	                   customerService.findById(cId).ifPresentOrElse(cust -> {
	                        if (cust.getCart().getItems().isEmpty()) {
	                            System.out.println("Cart empty!");
	                        } else {
	                            Order ord = orderService.placeOrder(cust);
	                            System.out.println("Order placed! Your order ID is: " + ord.getOrderId());
	                        }
	                    }, () -> System.out.println("Customer not found."));
	                    break;
	                case 6:
	                    System.out.println("Orders:");
	                    orderService.getAllOrders().forEach(System.out::println);
	                    break;
	                case 7: System.out.println("Exiting Customer Module"); return;
	                default: System.out.println("Invalid option"); break;
	            }
	        }
	    }
	}


