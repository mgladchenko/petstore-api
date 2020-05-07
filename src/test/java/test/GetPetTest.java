package test;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Category;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPetTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Before
    public void createPet() {
        //Pet pet = new Pet("0", "chupacabra", Status.AVAILABLE);
        Pet pet = Pet.builder()
                .id("0")
                .name("chupacabra")
                .status(Status.AVAILABLE)
                //.category(new Category("0", "NEW"))
                .category(Category.builder().build())
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void getPetByStatus() {
        petEndpoint.getPetByStatus(Status.AVAILABLE);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void getPetById() {
        petEndpoint.getPet(createdPetId);
    }
}