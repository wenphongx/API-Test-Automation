import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class SmockTest extends BaseTest{
    private static int userId;

    @Test(groups = "e2e", priority = 1, description = "Test Get Existing User")
    public void testCreateNewUser() {
        userId = API_Utils.createUser(TOKEN);
    }

    @Test(groups = "e2e", priority = 2, description = "Test Get Existing User")
    public void testGetUser() {
        API_Utils.getUserAndVerify(TOKEN, String.valueOf(userId), 200);
    }

    @Test(groups = "e2e", priority = 3, description = "Test Update User", dependsOnMethods = "testGetUser")
    public void testUpdateUser() {
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name", "Updated Name");
        updatedData.put("status", "inactive");

        API_Utils.updateUser(userId, updatedData, 200, TOKEN);
    }

    @Test(groups = "e2e", priority = 4, description = "Test Delete User", dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        API_Utils.deleteUser(TOKEN, userId, 204);
    }
}
