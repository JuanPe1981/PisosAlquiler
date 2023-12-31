package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.model.MenuActivityModel;

import java.util.List;

public class MenuActivityPresenter implements MenuActivityContract.Presenter,
    MenuActivityContract.Model.OnLoadHousesListener{

    private MenuActivityModel model;
    private MenuActivityContract.View view;

    public MenuActivityPresenter (MenuActivityContract.View view) {
        this.model = new MenuActivityModel();
        this.view = view;
    }

    @Override
    public void loadAllHouses(User user) {
        model.getAll(this, user);
    }

    @Override
    public void onLoadHousesSuccess(List<HouseDto> housesDto) {
        view.showHouses(housesDto);
    }

    @Override
    public void onLoadHousesError () {
        view.showMessage();
    }
}
