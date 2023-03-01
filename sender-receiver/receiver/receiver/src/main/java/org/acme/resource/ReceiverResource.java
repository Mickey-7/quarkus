package org.acme.resource;

import io.vertx.core.json.JsonObject;
import org.acme.model.ReceiverModel;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/receiver")
public class ReceiverResource {

    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceiverMethod(@Context HttpHeaders httpHeaders, String payload){
        JsonObject jsonObject = new JsonObject();

        //print header content
        httpHeaders.getRequestHeaders().forEach((key,value) -> {
            System.out.println(key+" : "+value);
            jsonObject.put(key,value);

        });
        System.out.println(jsonObject);
        //print payload
        System.out.println("Payload : "+payload);

        //create response
        ReceiverModel receiverModel = new ReceiverModel();
        receiverModel.setTransactionId("UB2252");
        receiverModel.setTranDate("22-08-2022");
        return Response.status(Response.Status.OK)
                .entity(Entity.json(receiverModel))
                .build();
    }

}
