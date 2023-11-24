package com.svalero.pisosalquiler.api;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.Message;
import com.svalero.pisosalquiler.domain.User;

import retrofit2.Call;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoApiInterface {

    @GET("user/login")
    Call<User> getLogin(@Query("userName") String userName, @Query("password") String password);

//    @GET("house")
//    Call<List<House>> getHouses();

    @GET("housesDto")
    Call<List<HouseDto>> getHousesDto();

    @GET("house/{idHouse}")
    Call<House> getHouse(@Path("idHouse") String idHouse);

    @GET("ad")
    Call<List<Ad>> getAds();

//    @GET("adsDto")
//    Call<List<AdDto>> getAdsDto();

//    @GET("ad/{adId}")
//    Call<Ad> getAd(@Path("adId") long adId);

//    @GET("ad/house/{house}")
//    Call<List<Ad>> getAllAdsHouse(@Path("house") House house);

    @POST("ad")
    Call<AdInDto> addAd(@Body AdInDto adInDto);

//    @DELETE("ad/{adId}")
//    Call<Void> deleteAd (@Path("adId") long adId);
//    @GET("messagesDto")
//    Call<List<MessageDto>> getMessagesDto();

    @GET("messages")
    Call<List<Message>> getMessages();

    @POST("messageDto")
    Call<MessageInDto> addMessage (@Body MessageInDto messageInDto);

    @PATCH("ad/{idAd}")
    Call<AdPatchDto> updateAdState (@Path("idAd") long idAd, @Body AdPatchDto adPatchDto);

    @PATCH("user/{idUser}")
    Call<UserPatchDto> updatePasswordUser(@Path("idUser") long idUser, @Body UserPatchDto userPatchDto);

}
