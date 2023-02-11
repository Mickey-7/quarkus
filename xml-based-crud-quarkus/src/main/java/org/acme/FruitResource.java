package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class FruitResource {
    private final List<Fruit> fruitList = new ArrayList<>();
    @GET
    public List<Fruit> getFruit() {
        fruitList.add(new Fruit("Apple", "Crunchy fruit"));
        fruitList.add(new Fruit("Kiwi", "Delicious fruit"));

        return fruitList;
    }

    @POST
    public List<Fruit> addFruit(Fruit fruit){
         fruitList.add(fruit);
         return fruitList;
    }
}
