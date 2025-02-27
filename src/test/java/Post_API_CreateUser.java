import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class Post_API_CreateUser extends BaseTest {

    private static JsonNode testData;

    @BeforeClass
    public static void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        testData = objectMapper.readTree(new File("src/test/resources/testData.json"));
    }
    @Test(priority = 1, description = "Test Create User with Valid data")
    public void testCreateUser() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", testData.get("email_prefix").asText() + System.currentTimeMillis() + testData.get("email_domain").asText());
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        API_Utils.createAndVerifyUser(TOKEN,userData, 201);
    }

    @Test(priority = 2, description = "Test Create User with Duplicate Email")
    public void testCreateUserDuplicateEmail() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", "madhuri_dr_johar@maggio.tes");
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        API_Utils.createAndVerifyUser(TOKEN,userData, 422);
    }

    @Test(priority = 3, description = "Test Create User Without Email")
    public void testCreateUserWithoutEmail() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        API_Utils.createAndVerifyUser(TOKEN,userData, 422);
    }

    @Test(priority = 4, description = "Test Create User With Invalid Status")
    public void testCreateUserInvalidStatus() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", "janedoe" + System.currentTimeMillis() + "@example.com");
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", "unknown");

        API_Utils.createAndVerifyUser(TOKEN,userData, 422);
    }

    @Test(priority = 5, description = "Test Create User With Invalid Gender")
    public void testCreateUserInvalidGender() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", "chrisdoe" + System.currentTimeMillis() + "@example.com");
        userData.put("gender", "other");
        userData.put("status", testData.get("status").asText());

        API_Utils.createAndVerifyUser(TOKEN,userData, 422);
    }

    @Test(priority = 6, description = "Test Create User Without Token")
    public void testCreateUserWithoutToken() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", "testuser" + System.currentTimeMillis() + "@example.com");
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        Response response = given()
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 401, "Expected 401 but got " + response.getStatusCode());
    }

    @Test(priority = 7, description = "Test Create User With Invalid Token")
    public void testCreateUserWithInvalidToken() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", "testuser" + System.currentTimeMillis() + "@example.com");
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        Response response = given()
                .header("Authorization", "Bearer invalid_token")
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 401, "Expected 401 but got " + response.getStatusCode());
    }
}
