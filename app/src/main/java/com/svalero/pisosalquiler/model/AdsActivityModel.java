package com.svalero.pisosalquiler.model;

import android.content.Context;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsActivityModel implements AdsActivityContract.Model {

    private Context context;

    public AdsActivityModel (Context context) {
        this.context = context;
    }

    @Override
    public void getAllAdsHouse (OnLoadAdsListener listener, HouseDto houseDto) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<AdDto>> callAdsDto = todoApi.getAdsDto();
        callAdsDto.enqueue(new Callback<List<AdDto>>() {
            @Override
            public void onResponse(Call<List<AdDto>> call, Response<List<AdDto>> response) {
                List<AdDto> adsDtoHouse = new ArrayList<>();
                List<AdDto> adsDto = response.body();
                for (AdDto adDto : adsDto) {
                    if(adDto.getIdHouse() == houseDto.getIdHouse()) {
                        adsDtoHouse.add(adDto);
                    }
                }
                listener.onLoadAdsSuccess(adsDtoHouse);
            }
            @Override
            public void onFailure(Call<List<AdDto>> call, Throwable t) {
                String message = "Error al llamar a la API";
                listener.onLoadAdsError(message);
            }
        });
    }
}
