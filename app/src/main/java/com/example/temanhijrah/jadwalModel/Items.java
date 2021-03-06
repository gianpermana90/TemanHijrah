package com.example.temanhijrah.jadwalModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("data")
    @Expose
    private Jadwal data;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("debug")
    @Expose
    private Debug debug;
    @SerializedName("status")
    @Expose
    private String status;

    public Jadwal getData() {
        return data;
    }

    public void setData(Jadwal data) {
        this.data = data;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}