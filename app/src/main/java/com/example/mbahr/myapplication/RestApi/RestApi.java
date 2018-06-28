package com.example.mbahr.myapplication.RestApi;

import com.example.mbahr.myapplication.Models.Sonuc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @FormUrlEncoded
    @POST("/sartliekle.php")
    Call<Sonuc> addUser(@Field("email") String email, @Field("isim") String ad, @Field("soyad") String soyad, @Field("sifre") String sifre);


    @GET("/login.php")
    Call<Sonuc> authenticate(@Query("email") String login_email, @Query("sifre") String login_sifre);
}
