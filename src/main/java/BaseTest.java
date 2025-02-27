import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static final String BASE_URL = "https://gorest.co.in/public/v2";
    protected static String TOKEN = "0253d0e32e79e36d3da8fd9bce3054e329132cb6343303925d829ca6e2b43129";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;

        if (TOKEN == null || TOKEN.trim().isEmpty()) {
            throw new IllegalStateException("❌ ERROR: GOREST_API_TOKEN is not set or is empty.");
        }

        System.out.println("✅ Using Token: " + TOKEN);
    }
}
