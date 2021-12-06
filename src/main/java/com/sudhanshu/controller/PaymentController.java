package com.sudhanshu.controller;

import com.sudhanshu.model.Booking;

import java.util.HashMap;
import java.util.Map;

public class PaymentController {
    
    private TicketBookingController ticketBookingController;
    private LockController lockController;
    private Map<Booking, Integer> bookingFailures = new HashMap<>();
    private int retries;

    public PaymentController(TicketBookingController ticketBookingController, LockController lockController, int retries) {
        this.ticketBookingController = ticketBookingController;
        this.retries = retries;
        this.lockController = lockController;
    }

    public void paymentFailed(String bookingId,  final String user) {
       processPaymentFailed(ticketBookingController.getBooking(bookingId), user);
    }

    public void paymentSuccess(String bookingId,  final String user) {
        ticketBookingController.confirmBooking(ticketBookingController.getBooking(bookingId), user);
    }


    public void processPaymentFailed(Booking booking, String user) {
        if (!booking.getUserId().equals(user)) {
            throw new RuntimeException();
        }
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }

        int currentFailuresCount = bookingFailures.get(booking);
        int newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);

        if (newFailuresCount > retries) {
            lockController.unlockSeats(booking.getShow(), booking.getSeats(), booking.getUserId());
        }
    }
}
