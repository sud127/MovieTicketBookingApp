package com.sudhanshu.model;

import java.util.List;
import static com.sudhanshu.constants.Constant.*;

public class Booking {

    private String id;
    private Show show;
    private String userId;
    private List<Seat> seats;
    private String bookingStatus;

    public Booking(String id, Show show, String userId, List<Seat> seats, String bookingStatus) {
        this.id = id;
        this.show = show;
        this.userId = userId;
        this.seats = seats;
        this.bookingStatus = bookingStatus;
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public String getUserId() {
        return userId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void confirmBooking() {
        this.bookingStatus = CONFIRMED;
    }

    public void timeoutBooking() {
        this.bookingStatus = TIMEDOUT;
    }

    public void cancelBooking() {
        this.bookingStatus = CANCELLED;
    }

}
