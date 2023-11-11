package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.model.MenuActivityModel;
import com.svalero.pisosalquiler.view.MenuActivityView;

import java.util.List;

public class MenuActivityPresenter implements MenuActivityContract.Presenter,
    MenuActivityContract.Model.OnLoadHousesListener{

    private MenuActivityModel model;
    private MenuActivityView view;

    public MenuActivityPresenter (MenuActivityView view) {
        this.model = new MenuActivityModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllHouses() {
        model.getAll(this);
    }

    @Override
    public void onLoadHousesSuccess(List<House> houses) {
        view.showHouses(houses);
    }

    @Override
    public void onLoadHousesError (String message) {
        view.showMessage(message);
    }
}
