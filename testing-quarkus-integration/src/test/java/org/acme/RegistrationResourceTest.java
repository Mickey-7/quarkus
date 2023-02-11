package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.model.UserModel;
import org.acme.util.PasswordGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.acme.controller.RegistrationResource;
import org.mockito.Mockito;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(RegistrationResource.class)
public class RegistrationResourceTest {
    @TestHTTPResource
    @TestHTTPEndpoint(RegistrationResource.class)
    URL url;

    //dependency : quarkus-junit5-mockito
    @InjectMock
    PasswordGenerator passwordGenerator;

    //need to add dependency : quarkus-smallrye-openapi to pass
    @Test
    @Order(1)
    public void shouldRegisterAUser(){
        Mockito.when(passwordGenerator.generate())
                .thenReturn("my-secret-password");
        final UserModel userModel = new UserModel();
        userModel.setUsername("ALEX");
        userModel.setEmail("asotobu@example.com");

        System.out.println("user model :"+userModel);

        //dependency : rest-assured
        given()
                .body(userModel)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON)
                .when()
                .post()
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location",url+"/1");
    }

    @Test
    @Order(2)
    public void shouldFindUserByUsername(){
        given()
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON)
                .when().get("/{username}","ALEX")
                .then()
                .statusCode(200)
                .body("username",is("ALEX"))
                .body("email",is("asotobu@example.com"))
                .body("password",is("my-secret-password"));
    }
}
