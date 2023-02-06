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

@Path(CatalogueControllerAPIPaths.BASE_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogItemController {
    private Logger logger = LoggerFactory.getLogger(CatalogItemController.class);

    @Inject
    CatalogService catalogService;

    //getCatalogItems
    @GET
    @Path(CatalogueControllerAPIPaths.GET_ITEMS)
    public Response getCatalogItems(){
        //for RuntimeExceptionMapper  test only
        //throw new RuntimeException();
        logger.info("getting catalog items");
        //invoked CatalogItemList
        return Response.ok(new CatalogItemList(catalogService.getCatalogItems())).build();
    }

    //getCatalogItem
    @GET
    @Path(CatalogueControllerAPIPaths.GET_ITEM)
    public Response getCatalogItemBySku(@PathParam("sku") String skuNumber) throws ResourceNotFoundException {
        logger.info(String.format("getting catalog item with sku : %s",skuNumber));
        return Response.ok(catalogService.getCatalogItem(skuNumber)).build();
    }

    //addCatalogItem
    @POST
    @Path(CatalogueControllerAPIPaths.CREATE)
    public Response addCatalogItem(@Valid CatalogItem catalogItem){
        logger.info(String.format("adding catalog item with sku : %s",catalogItem.getSku()));
        Long id = catalogService.addCatalogItem(catalogItem);
        //invoked ResourceIdentity
        return Response.status(Response.Status.CREATED).entity(new ResourceIdentity(id)).build();
    }

    //deleteCatalogItem
    @DELETE
    @Path(CatalogueControllerAPIPaths.DELETE)
    public Response deleteCatalogItem(@PathParam("sku") String skuNumber) throws ResourceNotFoundException {
        logger.info(String.format("removing catalog item with sku : %s",skuNumber));
        catalogService.deleteCatalogItem(skuNumber);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    //updateCatalogItem
    @PUT
    @Path(CatalogueControllerAPIPaths.UPDATE)
    public Response updateCatalogItem(@PathParam("sku") String skuNumber, @Valid CatalogItem catalogItem) throws ResourceNotFoundException {
        logger.info(String.format("===> updating catalog item with sku : %s",skuNumber));
        catalogService.updateCatalogItem(catalogItem);
        return Response.ok().build();
    }
}
