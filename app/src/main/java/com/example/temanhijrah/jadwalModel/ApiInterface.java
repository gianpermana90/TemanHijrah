package com.example.temanhijrah.jadwalModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("?")
    Call<Items> getJadwalSholat(@Query("address") String location, @Query("datetime") String date);
}
