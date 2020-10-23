package com.kardelenapp.agnohesaplama;

/**
 * Created by mustafa on 1/21/2017.
 */

public class CustomListItemKayit {
    String name;
    int index;
    int mode;

    public CustomListItemKayit(String name,int index, int mode) {
        super();
        this.name = name;
        this.index = index;
        this.mode = mode;

    }

    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIndex(int index) {
        this.index = index;
    }



}