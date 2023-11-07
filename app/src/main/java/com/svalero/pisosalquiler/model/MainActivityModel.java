package com.svalero.pisosalquiler.model;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.svalero.pisosalquiler.domain.User;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MainActivityContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityModel implements MainActivityContract.Model {

    public Context context;

    public MainActivityModel(Context context) {
        this.context = context;
    }

    @Override
    public void getLogin(String userName, String password, OnGetLoginListener listener) {
        try {
            TodoApiInterface todoApi = TodoApi.buildInstance();
            Call<User> callUser = todoApi.getLogin(userName,password);
            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    listener.onGetLoginSuccess(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operación";
                    listener.onGetLoginError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
