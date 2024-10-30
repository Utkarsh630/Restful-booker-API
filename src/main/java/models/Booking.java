package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking
{
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private  String lastname;
    @JsonProperty("totalprice")
    private long totalprice;
    @JsonProperty("depositpaid")
    private Boolean depositpaid;
    @JsonProperty("bookingdates")
    private Bookingdates bookingdates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    public Booking(String firstname, String lastname, long totalprice, Boolean depositpaid, Bookingdates bookingdates, String additionalNeeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalNeeds = additionalNeeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public long getTotalprice() {
        return totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public Bookingdates getBookingDates() {
        return bookingdates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }
}
