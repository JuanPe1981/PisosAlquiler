package com.svalero.pisosalquiler.presenter;

import android.widget.Switch;

import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.model.AdsActivityModel;

import java.util.List;

public class AdsActivityPresenter implements AdsActivityContract.Presenter,
    AdsActivityContract.Model.OnLoadAdsListener,
    AdsActivityContract.Model.OnRegisterAd{

    private AdsActivityModel model;

    private AdsActivityContract.View view;

    public AdsActivityPresenter (AdsActivityContract.View view) {
        this.model = new AdsActivityModel();
        this.view = view;
    }
    @Override
    public void loadAllAdsHouse (HouseDto houseDto, Switch viewAll) {
        model.getAllAdsHouse (this, houseDto, viewAll);
    }

    @Override
    public void onLoadAdsSuccess (List<Ad> ads) {
        view.showAds(ads);
    }

    @Override
    public void onLoadAdsError () {
        view.showMessage();
    }




    @Override
    public void registerAd(AdInDto adInDto) {
        model.registerAd(this, adInDto);
    }
    @Override
    public void onRegisterAdSuccess(AdInDto adInDto) {
        view.showMessageRegister();
    }
    @Override
    public void onRegisterError() {
        view.showErrorAdd();
    }
}
