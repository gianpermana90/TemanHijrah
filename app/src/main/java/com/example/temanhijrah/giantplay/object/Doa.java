package com.example.temanhijrah.giantplay.object;

public class Doa {

    private String name;
    private boolean fav;

    public Doa() {
    }

    public Doa(String name, boolean fav) {
        this.name = name;
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

}
