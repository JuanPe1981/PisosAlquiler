package com.svalero.pisosalquiler.model;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.DetailHouseContract;
import com.svalero.pisosalquiler.domain.House;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHouseModel implements DetailHouseContract.Model {

    private Context context;

    public DetailHouseModel (Context context) {
        this.context = context;
    }

    @Override
    public void getHouse (String idHouse, OnGetDetailHouseListener listener) {
        try {
            TodoApiInterface todoApi = TodoApi.buildInstance();
            Call<House> callHouse = todoApi.getHouse(idHouse);
            callHouse.enqueue(new Callback<House>() {
                @Override
                public void onResponse(Call<House> call, Response<House> response) {
                    House house = response.body();
                    listener.onGetDetailHouseSuccess(house);
                }
                @Override
                public void onFailure(Call<House> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operación";
                    listener.onGetDetailHouseError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
