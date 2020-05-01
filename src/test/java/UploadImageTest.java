import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class UploadImageTest {

    @Steps
    private PetEndpoint petEndpoint;

    private long createdPetId;
    private final String fileName;

    public UploadImageTest(String fileName) {
        this.fileName = fileName;
    }

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"cat.jpg"},
                {"dog.jpg"}
        });
    }

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "chupacabra", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void uploadImage() {
        petEndpoint.uploadImage(createdPetId, fileName);
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}