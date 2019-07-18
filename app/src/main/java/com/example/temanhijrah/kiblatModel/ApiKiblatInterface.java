package com.example.temanhijrah.kiblatModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiKiblatInterface {
    @GET("{latitude}/{longitude}")
    Call<Item> getKiblatPosition(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
