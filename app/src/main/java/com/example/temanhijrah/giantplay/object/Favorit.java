package com.example.temanhijrah.giantplay.object;

public class Favorit {

    private long id;
    private String favType;
    private String surahNumber;
    private String surahName;
    private String surahTranslate;

    public Favorit() {
    }

    public Favorit(long id, String favType, String surahNumber, String surahName, String surahTranslate) {
        this.id = id;
        this.favType = favType;
        this.surahNumber = surahNumber;
        this.surahName = surahName;
        this.surahTranslate = surahTranslate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFavType() {
        return favType;
    }

    public void setFavType(String favType) {
        this.favType = favType;
    }

    public String getSurahNumber() {
        return surahNumber;
    }

    public void setSurahNumber(String surahNumber) {
        this.surahNumber = surahNumber;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public String getSurahTranslate() {
        return surahTranslate;
    }

    public void setSurahTranslate(String surahTranslate) {
        this.surahTranslate = surahTranslate;
    }
}
