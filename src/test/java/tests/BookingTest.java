package tests;

import base.BaseTest;
import endpoints.AuthEndpoints;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;
import models.Auth;
import models.Booking;
import models.Bookingdates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigLoader;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.notNullValue;

public class BookingTest extends BaseTest {
private static final Logger logger = LogManager.getLogger(BookingTest.class);
private static String bookingID;
private static String token;

    @Test(description = "Successful creation of a booking with valid data.", priority = 1)
    public void createBookingTest(){
        Bookingdates bookingdates = new Bookingdates(formatter.format(faker.date().future(10, TimeUnit.DAYS)),
                formatter.format(faker.date().future(15, TimeUnit.DAYS)));
        Booking bookingRequest = new Booking(faker.name().firstName(),
                faker.name().lastName(),
                faker.number().randomNumber(9,false),
                faker.bool().bool(),
                bookingdates,
                faker.food().dish());
        logger.info("Creating a booking with data: {}", bookingRequest);
        // Send POST request to create booking
        String requestBody = gson.toJson(bookingRequest);
        Response response = BookingEndpoints.createBooking(requestBody);
        // Verify the response status code
        response.then().statusCode(200).assertThat()
                        .body("bookingid",notNullValue());
        logger.info("Booking created successfully with status code 200");
        bookingID = response.jsonPath().getString("bookingid");
        logger.info("Booking created successfully with Booking ID: {}", bookingID);
    }

    @Test(description = "Update Booking", dependsOnMethods = {"createAuthTokenTest", "createBookingTest"})
    public void updateBookingTest(){
        Bookingdates bookingdates = new Bookingdates(formatter.format(faker.date().future(10, TimeUnit.DAYS)),
                formatter.format(faker.date().future(15, TimeUnit.DAYS)));

        Booking bookingRequest = new Booking(faker.name().firstName(),
                faker.name().lastName(),
                faker.number().randomNumber(9,false),
                faker.bool().bool(),
                bookingdates,
                faker.food().dish());

        String requestBody = gson.toJson(bookingRequest);

        Response response = BookingEndpoints.updateBooking(bookingID,requestBody,token);
        response.then().statusCode(200);
        logger.info("Booking details updated for ID {} : {}",bookingID, requestBody);
    }

    @Test(description = "Successful retrieval of a specific booking by Valid ID", priority = 2)
    public void getBookingTest(){
        Response response = BookingEndpoints.getBookingByID(bookingID);
        logger.info("Retrieving Booking by ID: {}", bookingID);
        response.then().statusCode(200);
        logger.info("Booking retrieved successfully and verified status code");
    }

    @Test(description = "Create Authorization Token", priority = 0)
    public void createAuthTokenTest(){
        Auth authRequest = new Auth(
                ConfigLoader.getUsername(),
                ConfigLoader.getPassword()
        );
        String requestBody = gson.toJson(authRequest);
        logger.info("Generating Token");
        Response response = AuthEndpoints.createAuthRequest(requestBody);
        response.then().statusCode(200).assertThat().body("token", notNullValue());
        logger.info("Token generated and request returned 200 code");
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token,"token should not be null");
    }

    @Test(priority = 4, description = "Delete Booking", dependsOnMethods = {"createAuthTokenTest", "createBookingTest", "updateBookingTest"})
    public static void deleteBookingTest(){
        Response response = BookingEndpoints.deleteBooking(bookingID, token);
        logger.info("Deleting booking details for Booking ID: {}", bookingID);
        response.then().log().all().statusCode(201);
        logger.info("Booking with {} deleted successfully",bookingID);
    }

}
