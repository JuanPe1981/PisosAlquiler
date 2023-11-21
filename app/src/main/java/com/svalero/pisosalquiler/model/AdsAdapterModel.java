package com.svalero.pisosalquiler.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.AdsAdapterContract;
import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsAdapterModel implements AdsAdapterContract.Model {


    @Override
    public void updateAdState(OnUpdateAdState listener, long idAd, AdPatchDto adPatchDto) {
        try {
            TodoApiInterface todoApi = TodoApi.buildInstance();
            Call<AdPatchDto> callAdPatchDto = todoApi.updateAdState(idAd, adPatchDto);
            callAdPatchDto.enqueue(new Callback<AdPatchDto>() {
                @Override
                public void onResponse(Call<AdPatchDto> call, Response<AdPatchDto> response) {
                    AdPatchDto adPatchDto= response.body();
                    listener.onUpdateAdSuccess(adPatchDto);
                }
                @Override
                public void onFailure(Call<AdPatchDto> call, Throwable t) {
                    String message = "Error al llamar a la API";
                    listener.onUpdateAdError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
