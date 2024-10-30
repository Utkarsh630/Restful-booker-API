package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingEndpoints{

    public static Response createBooking(String requestBody) {

        return given()
                .body(requestBody).post("/booking");
    }

    public static Response getBookingByID(String bookingID) {

        return given()
                .get("/booking/"+bookingID);
    }

    public static Response updateBooking(String bookingID, String requestBody, String token) {
        return given()
                .header("Cookie","token="+token)
                .body(requestBody).put("/booking/"+bookingID);
    }
    public static Response deleteBooking(String bookingID, String token) {
        return given()
                .header("Cookie","token="+token)
                .delete("/booking/"+bookingID);
    }


}
