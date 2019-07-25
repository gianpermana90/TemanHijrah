package com.example.temanhijrah.userModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUserInterface {
    @POST("register")
    @FormUrlEncoded
    Call<User> register(@Field("username") String username,
                        @Field("firstname") String firstname,
                        @Field("lastname") String lastname,
                        @Field("birthday") String birthday,
                        @Field("gender") String gender,
                        @Field("email") String email,
                        @Field("phone") String phone,
                        @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("username") String uname, @Field("password") String pass);

    @PUT("gantiPassword/{id}")
    @FormUrlEncoded
    Call<Result> gantiPassword(@Path("id") String id, @Field("newPassword") String newPass, @Field("accessToken") String accessToken);

    @GET("lupaPassword/{email}")
    Call<Result> lupaPassword(@Path("email") String email);

    @PUT("updateUser/{id}")
    @FormUrlEncoded
    Call<Result> editProfil(@Path("id") String id, @Field("firstname") String name, @Field("email") String email);
}
