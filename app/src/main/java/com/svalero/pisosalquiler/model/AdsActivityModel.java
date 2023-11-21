package com.svalero.pisosalquiler.model;

import android.content.Context;
import android.widget.Switch;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsActivityModel implements AdsActivityContract.Model {

    private Context context;

    public AdsActivityModel (Context context) {
        this.context = context;
    }

    @Override
    public void getAllAdsHouse (OnLoadAdsListener listener, HouseDto houseDto, Switch viewAll) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<Ad>> callAds = todoApi.getAds();
        callAds.enqueue(new Callback<List<Ad>>() {
            @Override
            public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
                List<Ad> adsHouse = new ArrayList<>();
                List<Ad> ads = response.body();
                if (viewAll.isChecked() == false) {
                    for (Ad ad : ads) {
                        if(ad.getHouse().getIdHouse() == houseDto.getIdHouse() && ad.getFinishedAd().equals(false)) {
                            adsHouse.add(ad);
                        }
                    }
                    listener.onLoadAdsSuccess(adsHouse);
                } else if (viewAll.isChecked() == true) {
                    for (Ad ad : ads) {
                        if(ad.getHouse().getIdHouse() == houseDto.getIdHouse()) {
                            adsHouse.add(ad);
                        }
                    }
                    listener.onLoadAdsSuccess(adsHouse);
                }

            }
            @Override
            public void onFailure(Call<List<Ad>> call, Throwable t) {
                String message = "Error al llamar a la API";
                listener.onLoadAdsError(message);
            }
        });
    }

    @Override
    public void registerAd(OnRegisterAd listener, AdInDto adInDto) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<AdInDto> callAd = todoApi.addAd(adInDto);
        callAd.enqueue(new Callback<AdInDto>() {
            @Override
            public void onResponse(Call<AdInDto> call, Response<AdInDto> response) {
                AdInDto adInDto = response.body();
                listener.onRegisterAdSuccess(adInDto);
            }
            @Override
            public void onFailure(Call<AdInDto> call, Throwable t) {
                String error = "Error al llamar a la API";
                listener.onRegisterError(error);
            }
        });
    }


}
