package org.acme;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.acme.error.Error;
import org.acme.model.CatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest

public class RestAssuredCatalogCRUDTest extends BaseTest{
    @BeforeEach
    public void setURL(){
        RestAssured.baseURI="http://localhost:8080/api/v1";
    }

    //healthCheck
    @Test
    @DisplayName("test if application is up by accessing health endpoint")
    public void test_applicationIsUp(){
        try {
            given()
                    .get("http://localhost:8080/q/health")
            .then()
                .assertThat().spec(prepareResponseSpecification(200))
            .and()
                .assertThat().body("status",equalTo("UP"));
        }catch (Exception e){
            fail("Error occurred while testing application health check", e);
        }
    }

    //addCatalogItem
    @Test
    @DisplayName("tes create catalog item")
    public void test_createCatalogItem(){
        try {
            //save catalog item
            postCatalogItem(prepareCatalogItem(prepareRandomSKUNumber()))
            .then()
                .assertThat().spec(prepareResponseSpecification(201))
            .and()
                .assertThat().body("id",greaterThan(0));
        }catch (Exception e){
            fail("error occurred while testing catalog item create endpoint",e);
        }
    }

    //getCatalogItems
    @Test
    @DisplayName("test get catalog items")
    public void test_getCatalogItems(){
        try {
            //save catalog item
            postCatalogItem(prepareCatalogItem(prepareRandomSKUNumber()));
            postCatalogItem(prepareCatalogItem(prepareRandomSKUNumber()));

            given()
                    .get("/")
                    .then()
                    .assertThat().spec(prepareResponseSpecification(200))
                    .and()
                    .assertThat().body("data",is(not(empty())));
        }catch (Exception e){
            fail("error occurred while testing fetch catalog items");
        }
    }

    //getCatalogItem
    @Test
    @DisplayName("test get catalog item")
    public void test_getCatalogItem(){
        try {
            //create catalog item
            CatalogItem catalogItem = prepareCatalogItem(prepareRandomSKUNumber());
            //save catalog item
            postCatalogItem(catalogItem);

            given()
                .pathParam("sku",catalogItem.getSku())
            .when()
                .get("/{sku}")
            .then()
                .assertThat().spec(prepareResponseSpecification(200))
            .and()
                .assertThat().body("name",equalTo(catalogItem.getName()))
            .and()
                .assertThat().body("category",equalTo(catalogItem.getCategory()));
        }catch (Exception e){
            fail("error occurred while testing fetch catalog item",e);
        }
    }

    //deleteCatalogItem
    @Test
    @DisplayName("test delete catalog item")
    public void test_deleteCatalogItem(){
        try {
            //create catalog item
            CatalogItem catalogItem = prepareCatalogItem(prepareRandomSKUNumber());
            //save catalog item
            postCatalogItem(catalogItem);

            given()
                    .pathParam("sku",catalogItem.getSku())
                    .when()
                    .delete("/{sku}")
                    .then()
                    .assertThat().spec(prepareResponseSpecification(204));

            given()
                    .pathParam("sku",catalogItem.getSku())
                    .when()
                    .get("/{sku}")
                    .then()
                    .assertThat().spec(prepareResponseSpecification(404));

        }catch (Exception e){
            fail("error occurred while testing catalog item delete",e);
        }
    }

    //updateCatalogItem
    @Test
    @DisplayName("test update catalog item")
    public void test_updateCatalogItem(){
        try {
            //create catalog item
            CatalogItem catalogItem = prepareCatalogItem(prepareRandomSKUNumber());
            //save catalog item
            postCatalogItem(catalogItem);

            //update catalog item
            catalogItem.setName("updated - "+catalogItem.getName());
            catalogItem.setDescription("updated - "+catalogItem.getDescription());

            given()
                .contentType("application/json")
                .body(catalogItem)
                .pathParam("sku",catalogItem.getSku())
            .when()
                .put("/{sku}")
            .then()
                .assertThat().spec(prepareResponseSpecification(200));

            //get updated catalog item with sku of catalog item created
            given()
                .pathParam("sku",catalogItem.getSku())
            .when()
                .get("/{sku}")
            .then()
                .assertThat().spec(prepareResponseSpecification(200))
            .and()
                .assertThat().body("name",equalTo(catalogItem.getName()))
            .and()
                .assertThat().body("category",equalTo(catalogItem.getCategory()));

        }catch (Exception e){
            fail("error occurred while testing catalog item update",e);
        }
    }


    //resourceNotFound
    @Test
    @DisplayName("test resource not found")
    public void test_resourceNotFound() {
        try {
            given()
                .pathParam("sku",prepareRandomSKUNumber())
                .get("/{sku}")
            .then()
                .assertThat().spec(prepareResponseSpecification(404));
        }catch (Exception e){
            fail("error occurred while testing resource not found",e);
        }
    }

    //validationErrors - constraint
    @Test
    @DisplayName("test validation error")
    public void test_validationErrors(){
        try{
            CatalogItem catalogItem = prepareCatalogItem(prepareRandomSKUNumber());
            catalogItem.setCategory("INVALID");
            Response response = postCatalogItem(catalogItem)
                    .then()
                    .assertThat().spec(prepareResponseSpecification(400))
                    .and()
                    .extract().response();
            List<Error> errors = Arrays.asList(response.getBody().jsonPath().getObject("errors",Error[].class));
            assertTrue(errors != null && errors.size() > 0);
            assertTrue(errors.get(0).getDescription().equalsIgnoreCase("invalid category provided"));
        }catch (Exception e){
            fail("error occurred while testing validation errors",e);
        }
    }

    //invalidRequest - not found exception
    @Test
    @DisplayName("test invalid request")
    public void test_invalidRequest(){
        try{
            //create catalog item via JsonObject
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.createObjectNode();
            ((ObjectNode) rootNode).put("name","INVALID");
            ((ObjectNode) rootNode).put("sku",prepareRandomSKUNumber());
            ((ObjectNode) rootNode).put("price","INVALID");

            String catalogItem = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

            Response response = given()
                    .contentType("application/json")
                        .body(catalogItem)
                        .post("/")
                    .then()
                        .assertThat().spec(prepareResponseSpecification(400))
                    .and()
                        .extract().response();

            List<Error> errors = Arrays.asList(response.getBody().jsonPath().getObject("errors",Error[].class));
            assertTrue(errors != null && errors.size() > 0);
            assertTrue(errors.get(0).getMessage().contains("invalid request"));

        }catch (Exception e){
            fail("error occurred while testing invalid request",e);
        }
    }

}
