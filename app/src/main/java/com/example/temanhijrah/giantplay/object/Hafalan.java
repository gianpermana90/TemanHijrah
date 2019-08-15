package com.example.temanhijrah.giantplay.object;

public class Hafalan {

    private String surat;
    private String translate;
    private int progress;

    public Hafalan() {
    }

    public Hafalan(String surat, String translate, int progress) {
        this.surat = surat;
        this.translate = translate;
        this.progress = progress;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
