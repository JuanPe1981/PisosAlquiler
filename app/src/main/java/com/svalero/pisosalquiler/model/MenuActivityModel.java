package com.svalero.pisosalquiler.model;

import android.content.Context;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.House;

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
    public void getAll(OnLoadHousesListener listener) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<House>> callHouses = todoApi.getHouses();
        callHouses.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                List<House> houses = response.body();
                listener.onLoadHousesSuccess(houses);
            }
            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {
                String message = "Error al llamar a la API";
                listener.onLoadHousesError(message);
            }
        });
    }
}
