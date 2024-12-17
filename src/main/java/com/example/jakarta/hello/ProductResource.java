package com.example.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.Optional;

@Path("/products")
public class ProductResource {
    private static ProductManager productManager = new ProductManager();

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        int productId = productManager.addProduct(product);
        URI resourceLocation = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(productId))
                .build();
        return Response.created(resourceLocation).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Optional<Product> product = productManager.getProductById(id);
        if (product.isPresent()) {
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        if (productManager.deleteProduct(id)) {
            return Response.ok("Product deleted successfully!").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product) {
        if (productManager.updateProduct(id, product).isPresent()) {
            return Response.ok("Product updated successfully!").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("query") @DefaultValue("") String query) {
        return Response.ok(productManager.searchProducts(query)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return Response.ok(productManager.getAllProducts()).build();
    }
}
