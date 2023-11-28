package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.DetailHouseContract;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.model.DetailHouseModel;
import com.svalero.pisosalquiler.view.DetailHouseView;

public class DetailHousePresenter implements DetailHouseContract.Presenter,
    DetailHouseContract.Model.OnGetDetailHouseListener {

    private DetailHouseModel model;

    private DetailHouseView view;

    public DetailHousePresenter (DetailHouseView view) {
        this.model = new DetailHouseModel();
        this.view = view;;
    }

    @Override
    public void loadHouse (String idHouse) {
        model.getHouse(idHouse, this);
    }

    @Override
    public void onGetDetailHouseSuccess(House house) {
        view.showHouse(house);
    }

    @Override
    public void onGetDetailHouseError (String message) {
        view.showError(message);
    }
}
