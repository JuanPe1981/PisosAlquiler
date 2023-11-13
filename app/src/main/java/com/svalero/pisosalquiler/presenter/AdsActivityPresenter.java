package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.model.AdsActivityModel;
import com.svalero.pisosalquiler.model.MenuActivityModel;
import com.svalero.pisosalquiler.view.AdsActivityView;
import com.svalero.pisosalquiler.view.MenuActivityView;

import java.util.List;

public class AdsActivityPresenter implements AdsActivityContract.Presenter,
    AdsActivityContract.Model.OnLoadAdsListener{

    private AdsActivityModel model;

    private AdsActivityView view;

    public AdsActivityPresenter (AdsActivityView view) {
        this.model = new AdsActivityModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllAdsHouse (HouseDto houseDto) {
        model.getAllAdsHouse (this, houseDto);
    }

    @Override
    public void onLoadAdsSuccess (List<AdDto> adsDto) {
        view.showAds(adsDto);
    }

    @Override
    public void onLoadAdsError (String message) {
        view.showMessage(message);
    }
}
