package com.sudhanshu.controller;

import com.sudhanshu.model.Seat;
import com.sudhanshu.model.Show;

import java.util.ArrayList;
import java.util.List;

public class SeatController {

    private  TicketBookingController bookingController;
    private  LockController lockController;

    public SeatController(  TicketBookingController bookingController,
                                       LockController lockController) {
        this.bookingController = bookingController;
        this.lockController = lockController;
    }

    public List<Seat> getAvailableSeats(Show show) {
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        List<Seat> unavailableSeats = bookingController.getBookedSeats(show);
        unavailableSeats.addAll(lockController.getLockedSeats(show));
        return unavailableSeats;
    }
}
