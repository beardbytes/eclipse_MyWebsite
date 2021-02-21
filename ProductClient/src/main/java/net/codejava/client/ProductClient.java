package net.codejava.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class ProductClient {
	private static String baseURI = "http://localhost:8080/MyWebsite/rest/products"; 
	
	public static void main(String[] args) {
		testList();
		//testGet();
		//testAdd();
		//testUpdate();
		testDelete();
		testList();
	}
	
	static void testDelete() {
		WebTarget target = getWebTarget();
		String productId = "1";
		
		Response response = target.path(productId).
											request().
											delete(Response.class);
		System.out.println(response);
	}
	
	static void testUpdate() {
		WebTarget target = getWebTarget();
		String productId = "10";
		Product product = new Product("IGlass",78.90f);
		Response  response = target.path(productId).request().put(
												Entity.entity(product, MediaType.APPLICATION_JSON),
												Response.class);
		System.out.println(response);
	}
	
	static void testAdd() {
		WebTarget target = getWebTarget();
		Product product = new Product("IPod",178.90f);
		Response  response = target.request().post(
												Entity.entity(product, MediaType.APPLICATION_JSON),
												Response.class);
		System.out.println(response.getLocation().toString());
	}
	
	static void testGet() {
		WebTarget target = getWebTarget();
		String productId = "2";
		Product product = target.path(productId)
							.request()
							.accept(MediaType.APPLICATION_JSON)
							.get(Product.class);
		System.out.println(product);
	}
	
	static void testList() {
		WebTarget target = getWebTarget();
		String response = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
			
		System.out.println(response);
	}
	
	static WebTarget getWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		return client.target(baseURI);
	}

}
