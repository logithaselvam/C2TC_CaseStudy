package com.fooddelivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

	public class OrderService {
	    private List<Order> orders = new ArrayList<>();
	    private AtomicInteger orderCounter = new AtomicInteger(1);

	    public Order placeOrder(Customer customer) {
	        Order o = new Order(orderCounter.getAndIncrement(), customer);
	        // copy cart items
	        customer.getCart().getItems().forEach(o::addItem);
	        orders.add(o);
	        // clear cart
	        customer.getCart().getItems().clear();
	        return o;
	    }

	    public List<Order> getAllOrders() { return orders; }

	    public Optional<Order> findById(int id) {
	        return orders.stream().filter(o -> o.getOrderId() == id).findFirst();
	    }
	}
	

