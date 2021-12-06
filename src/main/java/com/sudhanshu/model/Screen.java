package com.sudhanshu.model;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    private String id;
    private String name;
    private Theatre theatre;
    private List<Seat> seats;

    public Screen(String id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }
}
