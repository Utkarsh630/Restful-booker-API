package base;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigLoader;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {
    protected Faker faker;
    protected Gson gson;
    protected SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
    @BeforeClass
    public void setup(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getBaseUri())
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(20000L)).build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        faker = new Faker();
        gson = new Gson();
    }
}
