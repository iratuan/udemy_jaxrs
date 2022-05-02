package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.v1.dto.ShowRoomDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/showrooms")
public class ShowRoomResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllBrands(){
        return Response.ok("Listando todas os show rooms").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBrand(@RequestBody @NotNull ShowRoomDTO showRoomDTO){
        return Response.ok(showRoomDTO).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBrand(@RequestBody @NotNull ShowRoomDTO showRoomDTO){
        return Response.ok(showRoomDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBrand(@PathParam("id") @NotNull Long id){
        return Response.ok(id).build();
    }
}
