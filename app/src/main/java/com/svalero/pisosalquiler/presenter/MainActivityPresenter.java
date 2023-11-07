package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.MainActivityContract;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.model.MainActivityModel;
import com.svalero.pisosalquiler.view.MainActivityView;

public class MainActivityPresenter implements MainActivityContract.Presenter,
        MainActivityContract.Model.OnGetLoginListener {

    private MainActivityModel model;
    private MainActivityView view;

    public MainActivityPresenter (MainActivityView view) {
        this.model = new MainActivityModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loginUser(String userName, String password) {
        model.getLogin(userName, password, this);
    }

    @Override
    public void onGetLoginSuccess(User user) {
        view.showUserLogin(user);
    }

    @Override
    public void onGetLoginError(String message) {
        view.showError ("Error");
    }

}
