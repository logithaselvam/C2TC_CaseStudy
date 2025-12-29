package com.fooddelivery;
import java.util.ArrayList;
import java.util.List;

	public class Restaurant {
	    private int id;
	    private String name;
	    private List<FoodItem> menu = new ArrayList<>();

	    public Restaurant(int id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }
	    public List<FoodItem> getMenu() { return menu; }

	    public void addFoodItem(FoodItem item) { menu.add(item); }
	    public void removeFoodItemById(int foodId) {
	        menu.removeIf(f -> f.getId() == foodId);
	    }

	    @Override
	    public String toString() {
	        return "Restaurant ID: " + id + ", Name: " + name;
	    }
	}


