package com.fooddelivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

	public class CustomerService {
	    private List<Customer> customers = new ArrayList<>();

	    public void addCustomer(Customer c) { customers.add(c); }
	    public Optional<Customer> findById(int id) {
	        return customers.stream().filter(c -> c.getUserId() == id).findFirst();
	    }
	    public List<Customer> getAll() { return customers; }
	}



