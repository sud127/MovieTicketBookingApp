package com.sudhanshu.model;

public class Movie {

    private String id;
    private String name;
    private String type;


    public Movie(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
