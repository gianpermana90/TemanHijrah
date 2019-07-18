package com.example.temanhijrah.kiblatModel;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

public class Item {
    @SerializedName("data")
    public Object item;
    @SerializedName("location")
    private Object loc;
    @SerializedName("status")
    private String status_valid;

    public Object getLoc() {
        return loc;
    }

    public void setLoc(Object loc) {
        this.loc = loc;
    }

    public String getStatus_valid() {
        return status_valid;
    }

    public void setStatus_valid(String status_valid) {
        this.status_valid = status_valid;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public LinkedTreeMap getItem() {
        return (LinkedTreeMap) item;
    }

    public Item(List<Kiblat> item) {
        this.item = item;
    }
}
