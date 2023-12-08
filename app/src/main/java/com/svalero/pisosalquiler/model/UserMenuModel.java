package com.svalero.pisosalquiler.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.UserMenuContract;
import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMenuModel implements UserMenuContract.Model {
    @Override
    public void updatePassword(OnUpdatePassword listener, long idUser, UserPatchDto userPatchDto) {
        try {
            TodoApiInterface todoApi = TodoApi.buildInstance();
            Call<UserPatchDto> callUser = todoApi.updatePasswordUser(idUser, userPatchDto);
            callUser.enqueue(new Callback<UserPatchDto>() {
                @Override
                public void onResponse(Call<UserPatchDto> call, Response<UserPatchDto> response) {
                    UserPatchDto userPatchDto = response.body();
                    listener.onUpdatePasswordSuccess(userPatchDto);
                }
                @Override
                public void onFailure(Call<UserPatchDto> call, Throwable t) {
                    listener.onUpdatePasswordError();
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
