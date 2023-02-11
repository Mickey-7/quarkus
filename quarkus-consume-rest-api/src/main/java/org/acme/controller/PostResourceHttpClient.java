package org.acme.controller;

import org.acme.model.PostModel;
import org.acme.service.PostServiceHttpClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@Path("/api/v1/httpclient")
public class PostResourceHttpClient {
    @Inject
    PostServiceHttpClient postServiceHttpClient;

    @GET
    @Path("/posts")
    public List<PostModel> fetchAllPosts() throws IOException, InterruptedException {
        List<PostModel> postModels = postServiceHttpClient
                .fetchPosts("https://jsonplaceholder.typicode.com/posts",
                        PostModel.class);
        return postModels;
    }
}
