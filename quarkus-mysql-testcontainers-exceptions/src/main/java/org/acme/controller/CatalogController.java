package org.acme.controller;

import org.acme.exception.ResourceNotFoundException;
import org.acme.model.CatalogItem;
import org.acme.model.CatalogItemList;
import org.acme.model.ResourceIdentity;
import org.acme.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(CatalogControllerAPIPaths.BASE_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogController {
    private Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Inject
    CatalogService catalogService;

    //getCatalogItems
    @GET
    @Path(CatalogControllerAPIPaths.GET_ITEMS)
    public Response getCatalogItems(){
        //throw new RuntimeException(); - for testing RuntimeExceptionMapper
        logger.info("getting catalog items");
        return Response.ok(new CatalogItemList(catalogService.getCatalogItems())).build();
    }

    //getCatalogItem
    @GET
    @Path(CatalogControllerAPIPaths.GET_ITEM)
    public Response getCatalogItemBySku(@PathParam(value = "sku") String skuNumber) throws ResourceNotFoundException {
        logger.info(String.format("getting catalog item with sku : %s",skuNumber));
        return Response.ok(catalogService.getCatalogItem(skuNumber)).build();
    }

    //addCatalogItem
    @POST
    @Path(CatalogControllerAPIPaths.CREATE)
    public Response addCatalogItem(@Valid CatalogItem catalogItem){
        logger.info(String.format("adding catalog item with sku : %s",catalogItem.getSku()));
        logger.info("=== "+catalogItem.toString());
        Long id = catalogService.addCatalogItem(catalogItem);
        return Response.status(Response.Status.CREATED)
                .entity(new ResourceIdentity(id))
                .build();
    }

    //updateCatalogItem
    @PUT
    @Path(CatalogControllerAPIPaths.UPDATE)
    public Response updateCatalogItem(@PathParam(value = "sku") String skuNumber, @Valid CatalogItem catalogItem) throws ResourceNotFoundException {
        logger.info(String.format("===> updating catalog with sku : %s",skuNumber));
        catalogService.updateCatalogItem(catalogItem);
        return Response.ok().build();
    }

    //deleteCatalogItem
    @DELETE
    @Path(CatalogControllerAPIPaths.DELETE)
    public Response deleteCatalogItem(@PathParam(value = "sku") String skuNumber) throws ResourceNotFoundException {
        logger.info(String.format("removing catalog with sku : %s",skuNumber));
        catalogService.deleteCatalog(skuNumber);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
