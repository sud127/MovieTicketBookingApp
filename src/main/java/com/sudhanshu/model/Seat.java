package com.sudhanshu.model;

public class Seat {

    private String seatId;
    private String seatType;
    private String row;
    private String seatNumber;

    public Seat(String seatId, String seatType, String row, String seatNumber) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.row = row;
        this.seatNumber = seatNumber;
    }

    public String  getSeatId() {
        return seatId;
    }

    public String  getSeatType() {
        return seatType;
    }

    public String getRow() {
        return row;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

}
