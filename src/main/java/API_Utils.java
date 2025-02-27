import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class API_Utils {

    private static JsonNode testData;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            testData = objectMapper.readTree(new File("src/test/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error loading test data from JSON file");
        }
    }

    public static void getUserAndVerify(String token, String userId, int expectedStatusCode) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/" + userId)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    public static Response createAndVerifyUser(String token, Map<String, Object> userData, int expectedStatusCode) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected " + expectedStatusCode + " but got " + response.getStatusCode());

        return response;
    }
    public static Response updateUser(int userId, Map<String, Object> updatedData, int expectedStatusCode, String token) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updatedData)
                .when()
                .put("/users/" + userId)
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected " + expectedStatusCode + " but got " + response.getStatusCode());
        System.out.println("✅ User Updated Successfully: ID " + userId);
        return response;
    }

    public static int createUser(String token) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", testData.get("name").asText());
        userData.put("email", testData.get("email_prefix").asText() + System.currentTimeMillis() + testData.get("email_domain").asText());
        userData.put("gender", testData.get("gender").asText());
        userData.put("status", testData.get("status").asText());

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201, "Expected 201 but got " + response.getStatusCode());

        int newUserId = response.jsonPath().getInt("id");
        System.out.println("✅ Created User ID: " + newUserId);
        return newUserId;
    }

    public static void deleteUser(String token, int userId, int expectedStatusCode) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/users/" + userId)
                .then()
                .log().all()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected Status Code");

        if (expectedStatusCode == 204) {
            Response getResponse = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("/users/" + userId)
                    .then()
                    .extract().response();
            Assert.assertEquals(getResponse.getStatusCode(), 404, "User should be deleted but still exists.");
            System.out.println("🗑️ User Deleted Successfully: ID " + userId);

        }
    }
}
