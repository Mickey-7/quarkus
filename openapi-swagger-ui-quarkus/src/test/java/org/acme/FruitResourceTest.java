package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testList(){
        given()
                .when().get("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                    "name",containsInAnyOrder("Apple","Pineapple"),
                    "description", containsInAnyOrder("Winter fruit","Tropical fruit")
                );
    }

    @Test
    public void testAdd(){
        given()
                //for easy typing,
                //type it first on postman request body
                //then copy and paste it here
                .body("{\n" +
                        "    \"name\":\"Pearl\",\n" +
                        "    \"description\":\"Winter fruit\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "name",containsInAnyOrder("Apple","Pineapple","Pearl"),
                        "description", containsInAnyOrder("Winter fruit","Tropical fruit","Winter fruit")
                );

        given()
                .body("{\n" +
                        "    \"name\":\"Pearl\",\n" +
                        "    \"description\":\"Winter fruit\"\n" +
                        "}")
                .header("Content-Type",MediaType.APPLICATION_JSON)
                .when()
                .delete("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name",containsInAnyOrder("Apple","Pineapple"),
                        "description", containsInAnyOrder("Winter fruit","Tropical fruit")
                );
    }
}
