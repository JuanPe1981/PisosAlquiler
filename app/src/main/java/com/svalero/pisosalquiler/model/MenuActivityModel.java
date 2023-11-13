package com.svalero.pisosalquiler.model;

import android.content.Context;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivityModel implements MenuActivityContract.Model {

    private Context context;

    public MenuActivityModel(Context context) {
        this.context = context;
    }

    @Override
    public void getAll(OnLoadHousesListener listener, User user) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<HouseDto>> callHousesDto = todoApi.getHousesDto();
        callHousesDto.enqueue(new Callback<List<HouseDto>>() {
            @Override
            public void onResponse(Call<List<HouseDto>> call, Response<List<HouseDto>> response) {
                List<HouseDto> housesDtoUser = new ArrayList<>();
                List<HouseDto> housesDto = response.body();
                for (HouseDto houseDto : housesDto) {
                    if (houseDto.getUserAgencyId() == user.getIdUser()) {
                        housesDtoUser.add(houseDto);
                    }
                    if (houseDto.getUserProprietaryId() == user.getIdUser()) {
                        housesDtoUser.add(houseDto);
                    }
                    if (houseDto.getUserRenterId() == user.getIdUser()) {
                        housesDtoUser.add(houseDto);
                    }
                }
                listener.onLoadHousesSuccess(housesDtoUser);
            }
            @Override
            public void onFailure(Call<List<HouseDto>> call, Throwable t) {
                String message = "Error al llamar a la API";
                listener.onLoadHousesError(message);
            }
        });
    }
}
