package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bookingdates {
    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;

    public Bookingdates(String checkin, String checkout) {
        this.checkout = checkout;
        this.checkin = checkin;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}
