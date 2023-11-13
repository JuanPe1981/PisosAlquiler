package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;

import java.util.List;

public interface AdsActivityContract {

    interface Model {
        interface OnLoadAdsListener {
            void onLoadAdsSuccess (List<AdDto> adsDto);
            void onLoadAdsError (String message);
        }

        void getAllAdsHouse (OnLoadAdsListener listener, HouseDto houseDto);

    }

    interface Presenter {

        void loadAllAdsHouse (HouseDto houseDto);
    }

    interface View {

        void showAds(List<AdDto> adsDto);

        void showMessage(String message);
    }
}
