package org.acme.controller;

import org.acme.model.UserModel;
import org.acme.util.PasswordGenerator;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/registration")
public class RegistrationResource {

    @Inject
    PasswordGenerator passwordGenerator;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserByUsername(@PathParam("username") String username){
        return UserModel.findByUsername(username)
                .map(u -> Response.ok(u).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    //need to add dependency : quarkus-resteasy-jsonb to
    //resolve issue when testing post on postman
    //RESTEASY003200: Could not find message body reader for type: class org.acme.model.UserModel of content type: application/json
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(UserModel userModel){
        userModel.setPassword(passwordGenerator.generate());
        userModel.persist();


        URI userUri = UriBuilder.fromResource(RegistrationResource.class)
                .path("/"+userModel.id).build();
        System.out.println("id : "+userModel.id);

        return Response.created(userUri).build();
    }
}
