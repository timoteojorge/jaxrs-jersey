package br.com.alura.loja.modelo;


public class Product {

	private double price;
	private long id;
	private String name;
	private int quantity;
	
	public Product(long id, String name, double price, int quantity) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}

	
	
	public double getTotalPrice() {
		return getQuantity() * getPrice();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}

	
}
