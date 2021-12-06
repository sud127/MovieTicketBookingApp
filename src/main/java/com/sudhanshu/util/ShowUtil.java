package com.sudhanshu.util;

import com.sudhanshu.model.*;

import java.util.*;

public class ShowUtil {

    private  Map<String, Show> shows;

    public ShowUtil() {
        this.shows = new HashMap<>();
    }

    public Show getShow( String showId) {
        if (!shows.containsKey(showId)) {
            throw new RuntimeException();
        }
        return shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime,
                           Date endTime, Theatre theatre) {

        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, startTime, endTime, movie, screen, theatre);
        this.shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(Screen screen) {
         List<Show> showList = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().equals(screen)) {
                showList.add(show);
            }
        }
        return showList;
    }

}
