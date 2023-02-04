package org.acme.controller;

import org.acme.model.ItemEntity;
import org.acme.service.ItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
    @Inject
    ItemService itemService;

    @GET
    public List<ItemEntity> get(){
        return itemService.get();
    }

    @POST
    public void create(ItemEntity item){
        itemService.create(item);
    }

    @PUT
    public void update(ItemEntity item){
        itemService.update(item);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id){
        itemService.delete(Long.valueOf(id));
    }
}
