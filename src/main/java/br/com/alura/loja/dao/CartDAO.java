package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Cart;
import br.com.alura.loja.modelo.Product;

public class CartDAO {
	
	private static Map<Long, Cart> db = new HashMap<Long, Cart>();
	private static AtomicLong counter = new AtomicLong(1);
	
	static {
		Product videogame = new Product(6237, "Videogame 4", 4000, 1);
		Product sport = new Product(3467, "Sport game", 60, 2);
		Cart cart = new Cart()
								.add(videogame)
								.add(sport)
								.to("3185 Vergueiro St, 8th floor", "Sao Paulo")
								.setId(1l);
		db.put(1l, cart);
	}
	
	public void add(Cart cart) {
		long id = counter.incrementAndGet();
		cart.setId(id);
		db.put(id, cart);
	}
	
	public Cart search(Long id) {
		return db.get(id);
	}
	
	public Cart remove(long id) {
		return db.remove(id);
	}

}
