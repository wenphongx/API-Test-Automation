import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class Get_API_GetUser extends BaseTest {

    private JsonNode testData;

    @BeforeClass
    public void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        testData = objectMapper.readTree(new File("src/test/resources/testData.json"));
    }

    @Test(priority = 1, description = "Test Get Existing User")
    public void testGetExistingUser() {
        API_Utils.getUserAndVerify(TOKEN, testData.get("existingUserId").asText(), 200);
    }

    @Test(priority = 2, description = "Test Get Non-Existing User")
    public void testGetNonExistingUser() {
        API_Utils.getUserAndVerify(TOKEN, testData.get("nonExistingUserId").asText(), 404);
    }

    @Test(priority = 3, description = "Test Get Invalid-UserId")
    public void testGetInvalidUserId() {
        API_Utils.getUserAndVerify(TOKEN, testData.get("invalidUserId").asText(), 404);
    }

    @Test(priority = 4, description = "Test Get Deleted-User")
    public void testGetDeletedUser() {
        API_Utils.getUserAndVerify(TOKEN, testData.get("deletedUserId").asText(), 404);
    }

    @Test(priority = 5, description = "Test Get User Without Token")
    public void testGetUserWithoutToken() {
        API_Utils.getUserAndVerify("", testData.get("existingUserId").asText(), 200);
    }

    @Test(priority = 6, description = "Test Get User With Invalid Token")
    public void testGetUserWithInvalidToken() {
        API_Utils.getUserAndVerify("invalid_token", testData.get("existingUserId").asText(), 401);
    }
}
