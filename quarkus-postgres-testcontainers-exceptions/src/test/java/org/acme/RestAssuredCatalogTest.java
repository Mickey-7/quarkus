package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.acme.model.CatalogItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class RestAssuredCatalogTest extends BaseTest{

    @BeforeEach
    public void setURL(){
        RestAssured.baseURI="http://localhost:8080/api/v1";
    }

    @Test
    @DisplayName("test for getCatalogItems ")
    public void test_getCatalogItems(){
        postCatalogItem(prepareCatalogItem(prepareRandomSkUNumber()));
        postCatalogItem(prepareCatalogItem(prepareRandomSkUNumber()));

        given()
                .get("/")
                .then()
                .assertThat().spec(prepareResponseSpec(200))
                .and()
                .assertThat().body("data",is(not(empty())));
    }

//    @Test
//    @DisplayName("test for getCatalogItem ")
//    public void test_getCatalogItem(){
//        CatalogItem catalogItem = prepareCatalogItem(prepareRandomSkUNumber());
//        postCatalogItem(catalogItem);
//
//        given()
//                .pathParam("sku",catalogItem.getSku())
//                .when()
//                .get("/{sku}")
//                .then()
//                .assertThat().spec(prepareResponseSpec(200))
//                .and()
//                .assertThat().body("name",equalTo(catalogItem.getName()))
//                .and()
//                .assertThat().body("category",equalTo(catalogItem.getCategory()));
//    }
//
//    @Test
//    @DisplayName("test for addCatalogItem ")
//    public void test_addCatalogItem(){
//        postCatalogItem(prepareCatalogItem(prepareRandomSkUNumber()))
//                .then()
//                .assertThat().spec(prepareResponseSpec(201))
//                .and()
//                .assertThat().body("id",greaterThan(0));
//    }
//
//    @Test
//    @DisplayName("test for deleteCatalogItem ")
//    public void test_deleteCatalogItem(){
//        CatalogItem catalogItem = prepareCatalogItem(prepareRandomSkUNumber());
//        postCatalogItem(catalogItem);
//
//        given()
//                .pathParam("sku",catalogItem.getSku())
//                .when()
//                .delete("/{sku}")
//                .then()
//                .assertThat().spec(prepareResponseSpec(204));
//
//    }
//
//    @Test
//    @DisplayName("test for updateCatalogItem ")
//    public void test_updateCatalogItem(){
//        CatalogItem catalogItem = prepareCatalogItem(prepareRandomSkUNumber());
//        postCatalogItem(catalogItem);
//
//        catalogItem.setName("updated - "+catalogItem.getName());
//        catalogItem.setDescription("updated - "+catalogItem.getDescription());
//
//        given()
//                .contentType("application/json")
//                .body(catalogItem)
//                .pathParam("sku",catalogItem.getSku())
//                .when()
//                .put("/{sku}")
//                .then()
//                .assertThat().spec(prepareResponseSpec(200));
//
//        // Get updated catalogue item with the sku of the catalogue item that is created and compare the response fields
//        given()
//                .pathParam("sku",catalogItem.getSku())
//                .when()
//                .get("/{sku}")
//                .then()
//                .assertThat().spec(prepareResponseSpec(200))
//                .and()
//                .assertThat().body("name",equalTo(catalogItem.getName()))
//                .and()
//                .assertThat().body("category",equalTo(catalogItem.getCategory()));
//    }
}
