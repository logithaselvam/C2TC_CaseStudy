package com.fooddelivery;
import java.util.LinkedHashMap;
import java.util.Map;

	public class Cart {
	    private Map<FoodItem, Integer> items = new LinkedHashMap<>();

	    public void addItem(FoodItem foodItem, int qty) {
	        items.put(foodItem, items.getOrDefault(foodItem, 0) + qty);
	    }

	    public void removeItem(FoodItem foodItem) {
	        items.remove(foodItem);
	    }

	    public Map<FoodItem, Integer> getItems() {
	        return items;
	    }

	    public double getTotalCost() {
	        double total = 0;
	        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
	            total += e.getKey().getPrice() * e.getValue();
	        }
	        return total;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
	            sb.append("Food Item: ").append(e.getKey().getName())
	              .append(", Quantity: ").append(e.getValue())
	              .append(", Cost: Rs. ").append(e.getKey().getPrice() * e.getValue())
	              .append("\n");
	        }
	        sb.append("Total Cost: Rs. ").append(getTotalCost());
	        return sb.toString();
	    }
	}


