import org.testng.annotations.Test;

public class Delete_API_DeleteUser extends BaseTest {

    @Test(priority = 1, description = "Test Delete Existing User")
    public void testDeleteUser() {
        int userId = API_Utils.createUser(TOKEN);
        API_Utils.deleteUser(TOKEN, userId, 204);
    }

    @Test(priority = 2, description = "Test Delete Non-Existing User")
    public void testDeleteNonExistingUser() {
        API_Utils.deleteUser(TOKEN, 9999999, 404);
    }

    @Test(priority = 3, description = "Test Delete User Without Token")
    public void testDeleteUserWithoutToken() {
        int userId = API_Utils.createUser(TOKEN);
        API_Utils.deleteUser("", userId, 404);
    }

    @Test(priority = 4, description = "Test Delete User With Invalid Token")
    public void testDeleteUserWithInvalidToken() {
        int userId = API_Utils.createUser(TOKEN);
        API_Utils.deleteUser("invalid_token", userId, 401);
    }
}
