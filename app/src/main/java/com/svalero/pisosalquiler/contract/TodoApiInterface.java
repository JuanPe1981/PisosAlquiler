package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Ad;

import retrofit2.Call;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TodoApiInterface {

    @GET("ad")
    Call<List<Ad>> getAds();

    @GET("ad/{adId}")
    Call<Ad> getAd(@Path("adId") long adId);

    @POST("ad")
    Call<Ad> addAd(@Body Ad ad);

    @DELETE("ad/{adId}")
    Call<Void> deleteAd (@Path("adId") long adId);
}
