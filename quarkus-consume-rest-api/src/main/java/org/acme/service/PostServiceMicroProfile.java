package org.acme.service;

import org.acme.model.PostModel;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/posts")
/*
@RegisterRestClient allows Quarkus to know
that this interface is meant to be available for
CDI injection as a REST Client
 */
@RegisterRestClient
public interface PostServiceMicroProfile {
    @GET
    List<PostModel> fetchALlPosts();
}
