package com.example.temanhijrah.jadwalModel;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("date_for")
    private String tanggal;
    @SerializedName("fajr")
    private String fajar;
    @SerializedName("shurooq")
    private String subuh;
    @SerializedName("dhuhr")
    private String zuhur;
    @SerializedName("asr")
    private String ashar;
    @SerializedName("maghrib")
    private String maghrib;
    @SerializedName("isha")
    private String isya;

    public String getTanggal() {
        return tanggal;
    }

    public String getImsak() {
        int imsakTime = convertToInt(fajar);
        imsakTime -= 10;
        return convertMinutesToString(imsakTime);
    }

    public String getFajar() {
        return convert(fajar);
    }

    public String getSubuh() {
        return convert(subuh);
    }

    public String getZuhur() {
        return convert(zuhur);
    }

    public String getAshar() {
        return convert(ashar);
    }

    public String getMaghrib() {
        return convert(maghrib);
    }

    public String getIsya() {
        return convert(isya);
    }

    private int convertToInt(String time) {
        String[] splitByColon = time.split(":");
        int hoursValue = Integer.parseInt(splitByColon[0]);

        String[] splitForMins = splitByColon[1].split(" ");

        Log.d("AM/PM", splitForMins[1]);
        if (splitForMins[1].equals("pm")) {
            hoursValue = hoursValue + 12;
        }

        int minutesValue = Integer.parseInt(splitForMins[0]);

        return 60 * hoursValue + minutesValue;
    }

    private String convertMinutesToString(int minutes) {
        int hours = minutes / 60;
        int minute = minutes % 60;

        String hourString;
        String minuteString;

        if (hours / 10 == 0) {
            hourString = "0" + hours;
        } else {
            hourString = String.valueOf(hours);
        }

        if (minute / 10 == 0) {
            minuteString = "0" + minute;
        } else {
            minuteString = String.valueOf(minute);
        }

        return hourString + ":" + minuteString;
    }

    private String convert(String time) {
        return convertMinutesToString(convertToInt(time));
    }
}
