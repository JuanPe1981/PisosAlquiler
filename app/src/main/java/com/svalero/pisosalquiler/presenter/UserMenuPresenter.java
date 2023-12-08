package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.UserMenuContract;
import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;
import com.svalero.pisosalquiler.model.UserMenuModel;
import com.svalero.pisosalquiler.view.UserMenuView;

public class UserMenuPresenter implements UserMenuContract.Presenter,
    UserMenuContract.Model.OnUpdatePassword {

    private UserMenuModel model;
    private UserMenuView view;

    public UserMenuPresenter (UserMenuView view) {
        this.model = new UserMenuModel();
        this.view = view;
    }

    @Override
    public void updatePassword (long idUser, UserPatchDto userPatchDto) {
        model.updatePassword(this, idUser, userPatchDto);
    }


    @Override
    public void onUpdatePasswordSuccess(UserPatchDto user) {
        view.showUpdateMessage(view.getString(R.string.password_modify_success));
    }

    @Override
    public void onUpdatePasswordError() {
        view.showUpdateError();
    }
}
