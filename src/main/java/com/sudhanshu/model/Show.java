package com.sudhanshu.model;

import java.util.Date;

public class Show {

    private String id;
    private Date startTime;
    private Date endTime;

    private Movie movie;
    private Screen screen;
    private Theatre theatre;


    public Show(String id, Date startTime, Date endTime, Movie movie, Screen screen, Theatre theatre) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.screen = screen;
        this.theatre = theatre;
    }

    public String getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Theatre getTheatre() {
        return theatre;
    }


}
