package com.fooddelivery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

	public class RestaurantService {
	    private List<Restaurant> restaurants = new ArrayList<>();

	    public void addRestaurant(Restaurant r) { restaurants.add(r); }
	    public Optional<Restaurant> findById(int id) {
	        return restaurants.stream().filter(r -> r.getId() == id).findFirst();
	    }
	    public List<Restaurant> getAll() { return restaurants; }
	}


