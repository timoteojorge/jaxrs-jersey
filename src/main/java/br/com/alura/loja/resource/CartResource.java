package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CartDAO;
import br.com.alura.loja.modelo.Cart;

@Path("carts")
public class CartResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{id}")
	public String search(@PathParam("id") Long id){
		Cart cart = new CartDAO().search(id);
		return cart.toXML();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response add(String content){
		Cart cart = (Cart)new XStream().fromXML(content);
		new CartDAO().add(cart);
		URI uri = URI.create("/carts/" + cart.getId());
		return Response.created(uri).build();
	}

}
