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
    final Random random = new Random();
    String prepareRandomSkUNumber(){
        return "SKUNumber-"+random.ints(1000,9999).findFirst().getAsInt();
    }

    CatalogItem prepareCatalogItem(String skuNumber){
        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setSku(skuNumber);
        catalogItem.setName("catalog Item - "+skuNumber);
        catalogItem.setDescription("catalog desc - "+skuNumber);
        catalogItem.setCategory(Category.BOOKS.getValue());
        catalogItem.setPrice(10.00);
        catalogItem.setInventory(10);
        catalogItem.setCreatedOn(new Date());
        return catalogItem;
    }

    Response postCatalogItem(CatalogItem catalogItem){
        RequestSpecification requestSpecification =
                given()
                        .contentType("application/json")
                        .body(catalogItem);
        return  requestSpecification.post("/");
    }
    ResponseSpecification prepareResponseSpec(int responseStatus){
        return new ResponseSpecBuilder()
                .expectStatusCode(responseStatus)
                .build();
    }


}
