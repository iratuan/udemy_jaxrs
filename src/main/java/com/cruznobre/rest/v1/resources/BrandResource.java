package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.core.service.BrandService;
import com.cruznobre.rest.v1.dto.BrandDTO;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/brands")
public class BrandResource {

    @Inject
    private BrandService service;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllBrands(){
        return Response.ok(service.listAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBrand(@RequestBody @NotNull Brand brand){
        Brand brandInserted = service.insert(brand);
        return Response.ok(brandInserted).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBrand(@RequestBody @NotNull BrandDTO brandDTO){
        return Response.ok(brandDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBrand(@PathParam("id") @NotNull Long id){
        return Response.ok(id).build();
    }
}
