package com.svalero.pisosalquiler.api;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.User;

import retrofit2.Call;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoApiInterface {

    @GET("user/login")
    Call<User> getLogin(@Query("userName") String userName, @Query("password") String password);

    @GET("house")
    Call<List<House>> getHouses();

    @GET("house/{idHouse}")
    Call<House> getHouse(@Path("idHouse") String idHouse);

    @GET("ad")
    Call<List<Ad>> getAds();

    @GET("ad/{adId}")
    Call<Ad> getAd(@Path("adId") long adId);

    @POST("ad")
    Call<Ad> addAd(@Body Ad ad);

    @DELETE("ad/{adId}")
    Call<Void> deleteAd (@Path("adId") long adId);



}
