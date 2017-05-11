package database;

import java.util.*;

public class Cart {

	private HashMap<Integer, CartItem> cartItems;

	public Cart() {
		cartItems = new HashMap<Integer, CartItem>();
	}

	public void addItemInCart(Movie movie, int qty) {
		if (cartItems.containsKey(movie.getId())) {
			CartItem existItem = cartItems.get(movie.getId());
			existItem.addQuantity(qty);
		} else {
			CartItem cartItem = new CartItem(movie, qty);
			cartItems.put(movie.getId(), cartItem);
		}
	}

	public void updateItemQty(Movie movie, int qty) {

		if (cartItems.containsKey(movie.getId())) {
			if (qty == 0) {
				cartItems.remove(movie.getId());
			} else {
				CartItem existItem = cartItems.get(movie.getId());
				existItem.setQty(qty);
			}
		}
	}

	public void removeItemInCart(Movie movie) {
		if (cartItems.containsKey(movie.getId())) {
			cartItems.remove(movie.getId());
		}
	}

	public void removeAllItems() {
		cartItems.clear();
	}

	public ArrayList<CartItem> getCartItems() {
		return new ArrayList<CartItem>(cartItems.values());
	}
}
