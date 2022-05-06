package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.service.ProductService;
import com.cruznobre.rest.shared.converter.ProductConverter;
import com.cruznobre.rest.shared.dto.ProductDTO;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProducts(@QueryParam("category") String category,
                                    @QueryParam("page") Integer page,
                                    @QueryParam("size") Integer size) {
        try {
            List<ProductDTO> list = new ArrayList<>();
            service.listAll(category, page, size).forEach(b -> {
                list.add(ProductConverter.toDTO(b));
            });
            return Response.ok(list).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/by-brand/{brandId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProductsByBrand(@NotNull @PathParam("brandId") Long brandId) {
        try {
            List<ProductDTO> list = new ArrayList<>();
            service.listAllByBrand(brandId).forEach(p -> {
                list.add(ProductConverter.toDTO(p));
            });
            return Response.ok(list).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@NotNull @PathParam("id") Long id) {
        try {
            return Response.ok(ProductConverter.toDTO(service.get(id))).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertProduct(@RequestBody @NotNull ProductDTO dto) {
        try {
            dto = ProductConverter.toDTO(service.insert(ProductConverter.toEntity(dto)));
        } catch (PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@NotNull @PathParam("id") Long id, @NotNull @RequestBody @NotNull ProductDTO dto) {
        try {
            dto = ProductConverter.toDTO(service.update(ProductConverter.toEntity(dto)));
        } catch (PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") @NotNull Long id) {
        try {
            service.delete(id);
        } catch (PersistenceExceptionCustom e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Status.NO_CONTENT).build();
    }
}