package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;
import models.Bookingdates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookingTest extends BaseTest {
private static final Logger logger = LogManager.getLogger(BookingTest.class);
private static int bookingID;
    @Test(description = "Create a new Booking")
    public void createBookingTest(){
        Bookingdates bookingdates = new Bookingdates("2023-12-01", "2023-12-10");
        Booking bookingRequest = new Booking("John",
                "Doe",
                150,
                true,
                bookingdates,
                "Breakfast");

        logger.info("Creating a booking with data: {}", bookingRequest);


        // Send POST request to create booking
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when().log().all()
                .post("/booking");

        // Verify the response status code
        response.then().log().all().statusCode(200);
        logger.info("Booking created successfully with status code 200");
        bookingID = response.then().extract().response().getBody().path("bookingid");
        Assert.assertNotNull(bookingID,"Booking ID should not be null");
        logger.info("Booking created successfully with Booking ID: {}", bookingID);

    }

    @Test(description = "Update Booking")
    public void updateBookingTest(){
        Bookingdates bookingdates = new Bookingdates("2020-01-01","2020-02-02");

        Booking booking = new Booking("John",
                "Wick", 165,true,
                bookingdates,"Dinner");

        Response response = given().contentType(ContentType.JSON)
                .when().log().all().body(booking).put("/booking/267");
        response.then().log().all().statusCode(200);
    }

    @Test(description = "Get Booking details")
    public void getBookingTest(){
        Response response = given().pathParam("id",bookingID).get("/booking/{id}");
        response.then().log().all().statusCode(200);
        logger.info("Booking retrieved successfully and verified status code");
    }



}
