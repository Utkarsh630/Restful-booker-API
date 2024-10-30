package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthEndpoints {

    public static Response createAuthRequest(String requestBody){

        return given().body(requestBody).post("/auth");
    }

}
