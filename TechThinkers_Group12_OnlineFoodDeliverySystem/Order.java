package com.fooddelivery;
import java.util.LinkedHashMap;
import java.util.Map;

	public class Order {
	    private int orderId;
	    private Customer customer;
	    private Map<FoodItem, Integer> items = new LinkedHashMap<>();
	    private String status = "Pending";
	    private DeliveryPerson deliveryPerson;
	    private String deliveryAddress;

	    public Order(int orderId, Customer customer) {
	        this.orderId = orderId;
	        this.customer = customer;
	    }

	    public int getOrderId() { return orderId; }
	    public Customer getCustomer() { return customer; }
	    public Map<FoodItem, Integer> getItems() { return items; }
	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }
	    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
	    public void setDeliveryPerson(DeliveryPerson dp) { this.deliveryPerson = dp; }
	    public String getDeliveryAddress() { return deliveryAddress; }
	    public void setDeliveryAddress(String addr) { this.deliveryAddress = addr; }

	    public void addItem(FoodItem f, int qty) {
	        items.put(f, items.getOrDefault(f, 0) + qty);
	    }

	    @Override
	    public String toString() {
	        return "Order{" +
	                "orderId=" + orderId +
	                ", customer=" + customer.getUsername() +
	                ", items=" + items +
	                ", status='" + status + '\'' +
	                ", deliveryPerson=" + (deliveryPerson != null ? deliveryPerson.getName() : "Not Assigned") +
	                '}';
	    }
	}


