package com.svalero.pisosalquiler.api;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.Message;
import com.svalero.pisosalquiler.domain.User;

import retrofit2.Call;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodoApiInterface {

    @GET("user/login")
    Call<User> getLogin(@Query("userName") String userName, @Query("password") String password);

    @GET("housesDto")
    Call<List<HouseDto>> getHousesDto();

    @GET("house/{idHouse}")
    Call<House> getHouse(@Path("idHouse") String idHouse);

    @GET("ad")
    Call<List<Ad>> getAds();

    @POST("ad")
    Call<AdInDto> addAd(@Body AdInDto adInDto);

    @GET("messages")
    Call<List<Message>> getMessages();

    @POST("messageDto")
    Call<MessageInDto> addMessage (@Body MessageInDto messageInDto);

    @PATCH("ad/{idAd}")
    Call<AdPatchDto> updateAdState (@Path("idAd") long idAd, @Body AdPatchDto adPatchDto);

    @PATCH("user/{idUser}")
    Call<UserPatchDto> updatePasswordUser(@Path("idUser") long idUser, @Body UserPatchDto userPatchDto);

}
