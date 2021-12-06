package com.sudhanshu.util;

import com.sudhanshu.model.Screen;
import com.sudhanshu.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreUtil {

    private  Map<String, Theatre> theatres;

    private ScreenUtil screenUtil;

    public TheatreUtil(ScreenUtil screenUtil) {
        this.theatres = new HashMap<>();
        this.screenUtil = screenUtil;
    }

    public Theatre getTheatre( String theatreId) {
        if (!theatres.containsKey(theatreId)) {
            throw new RuntimeException();
        }
        return theatres.get(theatreId);
    }

    public Theatre createTheatre( String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen addScreenInTheatre(String screenName, Theatre theatre) {
        Screen screen = screenUtil.createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

}
