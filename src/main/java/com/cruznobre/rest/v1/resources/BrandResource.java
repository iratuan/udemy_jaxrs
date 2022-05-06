package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.service.BrandService;
import com.cruznobre.rest.core.service.ProductService;
import com.cruznobre.rest.shared.converter.BrandConverter;
import com.cruznobre.rest.shared.dto.BrandDTO;
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

@Path("/brands")
public class BrandResource {

    @Inject
    private BrandService service;

    @Inject
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllBrands(@QueryParam("page") Integer page,
                                  @QueryParam("size") Integer size) {
        try {
            List<BrandDTO> list = new ArrayList<>();
            service.listAll(page, size).forEach(b -> {
                list.add(BrandConverter.toDTO(b));
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
    public Response getBrand(@NotNull @PathParam("id") Long id) {
        try {
            return Response.ok(BrandConverter.toDTO(service.get(id))).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBrand(@RequestBody @NotNull BrandDTO dto) {
        try {
            dto = BrandConverter.toDTO(service.insert(BrandConverter.toEntity(dto)));
        } catch (PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBrand(@NotNull @PathParam("id") Long id, @NotNull @RequestBody @NotNull BrandDTO dto) {
        try {
            dto = BrandConverter.toDTO(service.update(BrandConverter.toEntity(dto)));
        } catch (PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBrand(@PathParam("id") @NotNull Long id) {
        try {
            service.delete(id);
        } catch (PersistenceExceptionCustom e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Status.NO_CONTENT).build();
    }

}
