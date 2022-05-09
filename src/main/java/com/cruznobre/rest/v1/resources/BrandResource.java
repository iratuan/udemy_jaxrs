package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.service.BrandService;
import com.cruznobre.rest.core.service.ProductService;
import com.cruznobre.rest.shared.converter.BrandConverter;
import com.cruznobre.rest.shared.dto.BrandDTO;
import com.cruznobre.rest.shared.util.LinkBag;
import com.cruznobre.rest.shared.util.PaginableBag;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
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
                                  @QueryParam("size") Integer pageSize,
                                  @Context UriInfo uriInfo) {
        try {

            List<BrandDTO> list = new ArrayList<>();
            URI location = uriInfo.getAbsolutePath();
            service.listAll(page, pageSize).forEach(b -> {
                BrandDTO dto = BrandConverter.toDTO(b);
                dto.setLinks(getLinkBags(uriInfo, dto));
                list.add(dto);
            });

            LinkBag self = new LinkBag(location.toString(),"brands");
            List<LinkBag> links = new ArrayList<>();
            links.add(self);
            PaginableBag paginableBag = new PaginableBag(
                    Collections.singletonList(list),
                    links,
                    service.getTotal(),
                    page,
                    pageSize);
            return Response.ok(paginableBag).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrand(@NotNull @PathParam("id") Long id, @Context UriInfo uriInfo) {
        try {
            URI location = uriInfo.getAbsolutePath();
            BrandDTO dto = BrandConverter.toDTO(service.get(id));
            dto.setLinks(getLinkBags(uriInfo, dto));
            return Response.ok(dto).build();
        } catch (PersistenceException | PersistenceExceptionCustom e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertBrand(@RequestBody @NotNull BrandDTO dto, @Context UriInfo uriInfo) {
        try {
            dto = BrandConverter.toDTO(service.insert(BrandConverter.toEntity(dto)));
            dto.setLinks(getLinkBags(uriInfo, dto));
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
    public Response updateBrand(@NotNull @PathParam("id") Long id,
                                @NotNull @RequestBody @NotNull BrandDTO dto,
                                @Context UriInfo uriInfo) {
        try {
            dto = BrandConverter.toDTO(service.update(BrandConverter.toEntity(dto)));
            dto.setLinks(getLinkBags(uriInfo, dto));
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

    private List<LinkBag> getLinkBags(UriInfo uriInfo, BrandDTO b) {
        LinkBag self = new LinkBag(uriInfo.getAbsolutePath().toString(), "self");
        String uriProducts = uriInfo.getBaseUri() + "products/by-brand/"+ b.getId();
        LinkBag products = new LinkBag(uriProducts, "products");
        List<LinkBag> links = new ArrayList<>();
        links.add(self);
        links.add(products);
        return links;
    }
}
