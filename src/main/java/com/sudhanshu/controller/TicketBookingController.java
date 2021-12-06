package com.sudhanshu.controller;

import com.sudhanshu.model.Booking;
import com.sudhanshu.model.Seat;
import com.sudhanshu.model.Show;
import com.sudhanshu.util.ShowUtil;

import static com.sudhanshu.constants.Constant.*;

import java.util.*;

public class TicketBookingController {

    private LockController lockController = new LockController(1000);
    private Map<String, Booking>bookingMap = new HashMap();
    private ShowUtil showUtil;

    public TicketBookingController(ShowUtil showUtil)
    {
        this.showUtil = showUtil;
    }

    public String bookTicket(String theatreId, String movieId, String showId, List<Seat> requestedSeats, String userId)
    {
        List<Seat> bookedSeats = getBookedSeats(showUtil.getShow(showId));
        
        for(Seat s : bookedSeats)
        {
            if(requestedSeats.contains(s))
            {
                throw new RuntimeException("Some of the seats are already booked!");
            }
        }

        lockController.lockSeats(showUtil.getShow(showId),requestedSeats,userId);
        String bookingId = UUID.randomUUID().toString();
        Booking newBooking = new Booking(bookingId, showUtil.getShow(showId), userId, requestedSeats, CREATED);
        bookingMap.put(bookingId, newBooking);
        
        return bookingId;
    }

    public List<Seat> getBookedSeats(Show show)
    {
        List<Seat> bookedSeats = new ArrayList<>();
        for(Booking b : getBookingsForShow(show.getId()))
        {
            if(b.getBookingStatus()==CONFIRMED)
            {
                bookedSeats.addAll(b.getSeats());
            }
        }
        
        return bookedSeats;
        
    }
    
    public List<Booking> getBookingsForShow(String showId)
    {
        List<Booking> bookings = new ArrayList<>();
        for(Booking b : bookingMap.values())
        {
            if(b.getShow().getId().equals(showId))
            {
                bookings.add(b);
            }
        }
        return bookings;
    }

    public void confirmBooking(Booking booking,String userId) {
        for (Seat seat : booking.getSeats()) {
            if (!lockController.validateLock(booking.getShow(), seat, userId)) {
                throw new RuntimeException("Seats are not available for booking!");
            }
        }
        booking.confirmBooking();
    }

    public Booking getBooking(String bookingId) {
        return bookingMap.get(bookingId);
    }

}
