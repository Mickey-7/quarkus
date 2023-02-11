package org.acme.controller;

import org.acme.model.PostModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.List;

@Path("/api/v1/jaxrsclient")
public class PostResourceJAXRS {
    private final Client client = ClientBuilder.newBuilder().build();
    private final String url = "https://jsonplaceholder.typicode.com/posts";

    @GET
    @Path("/posts")
    public List<PostModel> fetchAllPosts(){
        List<PostModel> postModels = client.target(url)
                .request().get(new GenericType<>(){});
        return postModels;
    }
}
