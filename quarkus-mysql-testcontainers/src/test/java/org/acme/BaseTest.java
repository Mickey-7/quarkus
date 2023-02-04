package org.acme;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.acme.model.CatalogItem;
import org.acme.model.Category;

import java.util.Date;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {
    //We should consider creating new instance of CatalogueItem created with unique values,
    //else there will be unique constraint exceptions occurring in the application when
    //persisting the catalogue items to the database.
    //
    //Below reusable methods are created to create Catalogue Item with fields assigned with distinct values.

    final Random random = new Random();
    String prepareRandomSKUNumber(){
        return "SKUNumber-"+
                random.ints(1000,9999)
                        .findFirst()
                        .getAsInt();
    }
    CatalogItem prepareCatalogItem(String skuNumber){
        CatalogItem catalogItem = CatalogItem.of(
                skuNumber,
                "catalog item - "+skuNumber,
                "catalog description - "+skuNumber,
                Category.BOOKS.name(),
                10.00,
                10,
                new Date()
        );
        return catalogItem;
    }
    //As observed, we created prepareRandomSKUNumber method to generate unique SKU number which will
    //be passed to prepareCatalogueItem to create instance of Catalogue Item with random SKU number.
    //This will ensure unique constraint fields are kept unique when executing tests.

    //And finally, we will be creating one more reusable method to create instance of
    //ResponseSpecification based on the expected response HTTP Status code.
    //This method will be used in all test classes to verify if the
    //response received is with the expected response HTTP Status code.
    ResponseSpecification prepareResponseSpecification(int responseStatus){
        return new ResponseSpecBuilder()
                .expectStatusCode(responseStatus)
                .build();
    }

    //Below is such reusable method which handles creating catalogue item request and returning the response.
    Response postCatalogItem(CatalogItem catalogItem){
        RequestSpecification requestSpecification = given()
                .contentType("application/json")
                .body(catalogItem);
        return requestSpecification.post("/");
    }

}
