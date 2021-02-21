package net.codejava.ws;

import java.net.*;
import java.util.*;
 
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/products")
public class ProductResponse {
	private ProductDOA doa = ProductDOA.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
		public List<Product> list(){
			return doa.listAll();
		}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product product) throws URISyntaxException {
		int newProductId = doa.add(product);
		URI uri = new URI("/products/" + newProductId);
		return Response.created(uri).build();
		
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
	    Product product = doa.get(id);
	    if (product != null) {
	        return Response.ok(product, MediaType.APPLICATION_JSON).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).build();
	    }
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") int id, Product product) {
		product.setId(id);
		if(doa.update(product)) {
			return Response.ok().build();
		}
		else {
			return Response.notModified().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		if(doa.delete(id)) {
			return Response.ok().build();
		}
		else {
			return Response.notModified().build();
		}
	}
}
