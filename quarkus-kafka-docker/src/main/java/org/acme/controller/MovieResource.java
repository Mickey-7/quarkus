package org.acme.controller;

import org.acme.events.MovieProducer;
import org.acme.model.Movie;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieProducer producer;
    @POST
    public Response send(Movie movie){
        producer.sendMovieToKafka(movie);
        // Return an 202 - Accepted response.
        return Response.accepted().entity(movie.title).build();
    }

}
