package br.com.alura.loja.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Cart {

	private List<Product> products = new ArrayList<Product>();
	private String street;
	private String city;
	private long id;

	public Cart add(Product product) {
		products.add(product);
		return this;
	}

	public Cart to(String street, String city) {
		this.setStreet(street);
		this.setCity(city);
		return this;
	}

	public Cart setId(long id) {
		this.id = id;
		return this;
	}
	
	public long getId() {
		return id;
	}
	
	public void remove(long id) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void swap(Product product) {
		remove(product.getId());
		add(product);
	}

	public void changeQuantity(Product product) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product p = (Product) iterator.next();
			if(p.getId() == product.getId()) {
				p.setQuantity(product.getQuantity());
				return;
			}
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toXML() {

		return new XStream().toXML(this);
		
	}

	public String toJson() {
		
		return new Gson().toJson(this);
	}
	

	
}
