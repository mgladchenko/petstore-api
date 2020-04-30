import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class PetEndpoint {

    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{id}";
    private final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    private final static String DELETE_PET_BY_ID = "/pet/{id}";
    private final static String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    @Step
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", anyOf(is(petId), is(Status.AVAILABLE)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .when()
                .param("status", status)
                .get(GET_PET_BY_STATUS)
                .then()
                .body("status", everyItem(equalTo(status.toString())))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse uploadImage(long petId, String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return given()
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post(UPLOAD_IMAGE, petId)
                .then()
                .body("message", allOf(containsString("File uploaded"), containsString(file.getName())))
                .statusCode(SC_OK);
    }


}
