package com.example.temanhijrah.kiblatModel;

import com.google.gson.annotations.SerializedName;

public class Kiblat {
    @SerializedName("derajat")
    private float degree;
    @SerializedName("kabah")
    private String kabah;
    @SerializedName("kompas")
    private String kompas;
    @SerializedName("image")
    private String image;

    public String getKompas() {
        return kompas;
    }

    public void setKompas(String kompas) {
        this.kompas = kompas;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public String getKabah() {
        return kabah;
    }

    public void setKabah(String kabah) {
        this.kabah = kabah;
    }
}
