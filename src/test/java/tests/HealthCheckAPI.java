package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheckAPI extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HealthCheckAPI.class);

    @Test(description = "check if API is working")
    public void healthCheck(){
        Response response = given()
                .get("/ping").then().log().all().extract().response();

        response.then().statusCode(201);
        logger.info("API is working and returned 201 status code");
    }
}
