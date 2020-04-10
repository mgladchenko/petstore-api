import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class PetEndpoint {

    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{id}";
    private final static String DELETE_PET_BY_ID = "/pet/{id}";

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    public ValidatableResponse createPet(String body) {
        return given()
                .body(body)
                .when()
                .post(CREATE_PET)
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .log()
                .all()
                .body( "id", anyOf(is(petId), is("available")))
                .statusCode(200);
    }

    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);
    }


}
