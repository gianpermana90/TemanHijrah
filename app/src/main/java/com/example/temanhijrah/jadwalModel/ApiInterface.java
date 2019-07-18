package com.example.temanhijrah.jadwalModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("{periode}/daily.json?key=9e73216ac9e4e39c7f226b8f85ae0574")
    Call<Items> getJadwalSholat(@Path("periode") String periode);
}
