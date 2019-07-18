package com.example.temanhijrah.userModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUserInterface {
    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("username") String uname, @Field("password") String pass);

    @PUT("gantiPassword/{id}")
    @FormUrlEncoded
    Call<Result> gantiPassword(@Path("id") String id, @Field("newPassword") String newPass, @Field("accessToken") String accessToken);
}
