package org.acme;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class SenderResource {

    @ConfigProperty(name = "myapp.message")
    String message;

    @ConfigProperty(name = "myapp.array")
    String[] array;

    @ConfigProperty(name = "myapp.list")
    List<String> list;

    @ConfigProperty(name = "post.url")
    String postUrl;


    //onStart method is executed before start up
    void onStart(@Observes StartupEvent event) {
        System.out.println(message);

        for(String arr : array){
            System.out.println(arr);
        }

        list.forEach(System.out::println);

        sendMethod();

    }

    public void sendMethod(){
        // Create a JAX-RS client to make the POST request
        Client client = ClientBuilder.newClient();

        // Set up the request data
        String data = "{\n" +
                "    \"data\":\"data\"\n" +
                "}";

        // Make the POST request with header
        Response response = client.target(postUrl)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("X-IBM-Client-Id", "caadbccc-d92b-4929-a89c-c21bf0ca6ed8")
                .header("X-IBM-Client-Secret", "H1lE1sA7rI6wL4tJ6pF2mW8kE6oK5yK7aS5kI7jV3uY4yL5sB7")
                .post(Entity.entity(data, MediaType.APPLICATION_JSON_TYPE));

        // Close the client
        client.close();
        System.out.println(postUrl);
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

}
