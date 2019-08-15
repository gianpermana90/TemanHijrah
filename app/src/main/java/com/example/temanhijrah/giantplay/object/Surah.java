package com.example.temanhijrah.giantplay.object;

public class Surah {

    private String name;
    private String translate;
    private String fav;

    public Surah() {

    }

    public Surah(String name, String translate, String fav) {
        this.name = name;
        this.translate = translate;
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }
}
