package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.AdsAdapterView;
import com.svalero.pisosalquiler.contract.AdsAdapterContract;
import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;
import com.svalero.pisosalquiler.model.AdsAdapterModel;

public class AdsAdapterPresenter implements AdsAdapterContract.Presenter,
    AdsAdapterContract.Model.OnUpdateAdState {

    private AdsAdapterModel model;
    private AdsAdapterView view;

    public AdsAdapterPresenter (AdsAdapterView view) {
        this.model = new AdsAdapterModel();
        this.view = view;
    }

    @Override
    public void updateAdState(long idAd, AdPatchDto adPatchDto) {
        model.updateAdState (this, idAd, adPatchDto);
    }


    @Override
    public void onUpdateAdSuccess(AdPatchDto adPatchDto) {
        view.showUpdateMessage(String.valueOf(R.string.ad_modify_success));
    }

    @Override
    public void onUpdateAdError() {
        view.showUpdateError();
    }
}
