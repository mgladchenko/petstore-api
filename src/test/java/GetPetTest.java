
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GetPetTest {

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }

    @Test
    public void getPetById() {
        int id = 4;
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("id", anyOf(is(id), is("some_text")))
                .statusCode(200);
    }

    @Test
    public void getPetByStatusAvailable() {
        String status = "available";
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatusSold() {
        String status = "pending";
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/findByStatus?status={status}", status)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void getFindByStatusPending() {
        String status = "sold";
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/findByStatus?status={status}", status)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void addNewPetToStore() {
        String body = "{\n" +
                "  \"id\": 4564,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"UmaTurman\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void updatePetByDataForm() {
        String id = RandomStringUtils.randomNumeric(3);
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/x-www-form-urlencoded")
                .param("name", "Jackie")
                .param("status", "Sold")
                .when()
                .post("/pet/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void updateExistingPet() {
        String body = "{\n" +
                "  \"id\": 644,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Homer\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/pet")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void deletePetById() {
        int id = 4564;
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .delete("/pet/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

}