package com.sudhanshu.util;

import com.sudhanshu.model.Screen;
import com.sudhanshu.model.Seat;
import com.sudhanshu.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScreenUtil {

    private Map<String, Screen> screens = new HashMap<>();
    private Map<String, Seat> seats = new HashMap<>();

        public Seat addSeatInScreen(String rowNo, String seatNo, String seatType, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, seatType, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeat(seat);

        return seat;
    }

    protected Screen createScreen( String screenName,  Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }

    public Screen getScreen( String screenId) {
        if (!screens.containsKey(screenId)) {
            throw new RuntimeException("Invalid screen Id!");
        }
        return screens.get(screenId);
    }

    public Seat getSeat( String seatId) {
        if (!seats.containsKey(seatId)) {
            throw new RuntimeException();
        }
        return seats.get(seatId);
    }

}
