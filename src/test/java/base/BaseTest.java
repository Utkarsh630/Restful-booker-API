package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigLoader;

public class BaseTest {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI= ConfigLoader.getBaseUri();
    }
}
