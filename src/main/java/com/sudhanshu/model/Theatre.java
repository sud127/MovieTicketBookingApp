package com.sudhanshu.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private String id;
    private String name;
    private List<Screen> screens = new ArrayList<>();

    public Theatre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addScreen(Screen screen)
    {
        this.screens.add(screen);
    }

}
