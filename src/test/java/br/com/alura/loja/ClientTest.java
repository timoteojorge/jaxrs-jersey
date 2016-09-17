package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Cart;
import br.com.alura.loja.modelo.Product;
import br.com.alura.loja.modelo.Project;
import junit.framework.Assert;

public class ClientTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void startServer() throws IOException{
		server = Server.startServer();
		ClientConfig config =  new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}
	
	
	@Test
	public void testProjectResource(){
		String content = this.target.path("/projects/1").request().get(String.class);
		Assert.assertTrue(content.contains("Minha loja"));
	}
	
	@Test
	public void testReturnedCartIsExpectedOne(){
		String content = this.target.path("/carts/1").request().get(String.class);
		Cart cart = (Cart)new XStream().fromXML(content);
		Assert.assertEquals("3185 Vergueiro St, 8th floor", cart.getStreet());
	}
	
	@Test
	public void testReturnedProjectIsExpectedOne(){
		String content = this.target.path("/projects/1").request().get(String.class);
		Project project = (Project)new XStream().fromXML(content);
		Assert.assertEquals("Minha loja", project.getName());
	}
	
	@Test
	public void testPostAddCartResource(){
		Cart cart = new Cart();
		cart.add(new Product(314L, "Tablet", 999, 1));
		cart.setStreet("Rua Vergueiro");
		cart.setCity("Sao Paulo");
		
		String xml = cart.toXML();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML); 
		
		Response response = this.target.path("/carts").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		
		String content = this.client.target(response.getHeaderString("location")).request().get(String.class);
		Assert.assertTrue(content.contains("Tablet"));
	}
	
	
	@Test
	public void testPostAddProjectResource(){
		Project project = new Project();
		
		project.setName("New Project");
		project.setStartYear(2016);
		
		String xml = project.toXML();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML); 
		
		Response response = this.target.path("/projects/").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		
		String content = this.client.target(response.getHeaderString("location")).request().get(String.class);
		Assert.assertTrue(content.contains("New Project"));
	}
	
	
	@After
	public void stopServer(){
		server.stop();
	}

}
