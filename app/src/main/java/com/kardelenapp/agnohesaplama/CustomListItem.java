package com.kardelenapp.agnohesaplama;

/**
 * Created by mustafa on 1/21/2017.
 */

public class CustomListItem {
    String name;
    int puan;
    int kredi;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public int getKredi() {
        return kredi;
    }

    public void setKredi(int kredi) {
        this.kredi = kredi;
    }

    public CustomListItem(String name, int puan,int kredi) {
        super();
        this.name = name;
        this.puan = puan;
        this.kredi = kredi;
    }

}