package org.acme.controller;

import org.acme.model.PostModel;
import org.acme.service.PostServiceMicroProfile;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/v1/mprestclient")
public class PostResourceMicroProfile {
    @Inject
    @RestClient
    PostServiceMicroProfile postServiceMicroProfile;

    @GET
    @Path("/posts")
    public List<PostModel> fetchAllPosts(){
        return postServiceMicroProfile.fetchALlPosts();
    }
}
