import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Put_API_UpdateUser extends BaseTest {

    private static JsonNode testData;

    @BeforeClass
    public static void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        testData = objectMapper.readTree(new File("src/test/resources/testData.json"));
    }

    @Test(priority = 1, description = "Test Update Non-Existing User")
    public void testUpdateNonExistingUser() {
        int nonExistingUserId = 999999;
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", testData.get("name").asText());
        updatedData.put("status", testData.get("status").asText());

        API_Utils.updateUser(nonExistingUserId, updatedData, 404, TOKEN);
    }

    @Test(priority = 2, description = "Test Update User Without Token")
    public void testUpdateUserWithoutToken() {
        int existingUserId = testData.get("existingUserId").asInt();
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", testData.get("name").asText());
        updatedData.put("status", testData.get("status").asText());

        API_Utils.updateUser(existingUserId, updatedData, 401, "");
    }

    @Test(priority = 3, description = "Test Update User With Invalid Token")
    public void testUpdateUserWithInvalidToken() {
        int existingUserId = testData.get("existingUserId").asInt();
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", testData.get("name").asText());
        updatedData.put("status", testData.get("status").asText());

        API_Utils.updateUser(existingUserId, updatedData, 401, "invalid_token");
    }

    @Test(priority = 4, description = "Test Update User With Invalid Status")
    public void testUpdateUserInvalidStatus() {
        int existingUserId = testData.get("existingUserId").asInt();
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", testData.get("name").asText());
        updatedData.put("status", "unknown");

        API_Utils.updateUser(existingUserId, updatedData, 422, TOKEN);
    }

    @Test(priority = 5, description = "Test Update User With Invalid Gender")
    public void testUpdateUserInvalidGender() {
        int existingUserId = testData.get("existingUserId").asInt();
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", testData.get("name").asText());
        updatedData.put("gender", "other");
        updatedData.put("status", testData.get("status").asText());

        API_Utils.updateUser(existingUserId, updatedData, 422, TOKEN);
    }
}
