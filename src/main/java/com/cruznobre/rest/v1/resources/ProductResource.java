package com.cruznobre.rest.v1.resources;

import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.service.ProductService;
import com.cruznobre.rest.shared.converter.ProductConverter;
import com.cruznobre.rest.shared.dto.BrandDTO;
import com.cruznobre.rest.shared.dto.ProductDTO;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProducts(@QueryParam("category") String category,
                                    @QueryParam("page") Integer page,
                                    @QueryParam("size") Integer pageSize,
                                    @Context UriInfo uriInfo) {
        try {
            List<ProductDTO> list = new ArrayList<>();
            service.listAll(category, page, pageSize).forEach(p -> {
                ProductDTO dto = ProductConverter.toDTO(p);
                dto.setLinks(getLinkBags(uriInfo, dto.getBrand()));
                dto.getBrand().setLinks(getLinkBags(uriInfo, dto));
                list.add(dto);
            });
            LinkBag self = new LinkBag(uriInfo.getAbsolutePath().toString(),"products");
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
        } catch (NotFoundException e) {
            e.printStackTrace();
            return Response.status(Status.NOT_FOUND).build();
        }
    }


    @GET
    @Path("/by-brand/{brandId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllProductsByBrand(@NotNull @PathParam("brandId") Long brandId, @Context UriInfo uriInfo) {
        try {
            List<ProductDTO> list = new ArrayList<>();
            service.listAllByBrand(brandId).forEach(p -> {
                ProductDTO dto = ProductConverter.toDTO(p);
                dto.setLinks(getLinkBags(uriInfo));
                list.add(dto);
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
    public Response getProduct(@NotNull @PathParam("id") Long id, @Context UriInfo uriInfo) {
        try {
            ProductDTO dto = ProductConverter.toDTO(service.get(id));
            dto.setLinks(getLinkBags(uriInfo));
            return Response.ok(dto).build();
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
    public Response insertProduct(@RequestBody @NotNull ProductDTO dto, @Context UriInfo uriInfo) {
        try {
            dto = ProductConverter.toDTO(service.insert(ProductConverter.toEntity(dto)));
            dto.setLinks(getLinkBags(uriInfo));
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
    public Response updateProduct(@NotNull @PathParam("id") Long id,
                                  @NotNull @RequestBody @NotNull ProductDTO dto,
                                  @Context UriInfo uriInfo) {
        try {
            dto = ProductConverter.toDTO(service.update(ProductConverter.toEntity(dto)));
            dto.setLinks(getLinkBags(uriInfo));
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

    private List<LinkBag> getLinkBags(UriInfo uriInfo, BrandDTO b) {
        LinkBag self = new LinkBag(uriInfo.getAbsolutePath().toString() + "/" + b.getId(), "self");
        String uriProducts = uriInfo.getBaseUri() + "products/by-brand/"+ b.getId();
        LinkBag products = new LinkBag(uriProducts, "products");
        List<LinkBag> links = new ArrayList<>();
        links.add(self);
        links.add(products);
        return links;
    }

    private List<LinkBag> getLinkBags(UriInfo uriInfo, ProductDTO p) {
        String uriBrand = uriInfo.getBaseUri() + "brands/" + p.getBrand().getId();
        List<LinkBag> linksBrand = new ArrayList<>();
        LinkBag linkBrand = new LinkBag(uriBrand, "brands");
        linksBrand.add(linkBrand);
        return linksBrand;
    }
}