package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;

import java.util.List;

public interface AdsActivityContract {

    interface Model {
        interface OnLoadAdsListener {
            void onLoadAdsSuccess (List<Ad> ads);
            void onLoadAdsError (String message);
        }

        void getAllAdsHouse (OnLoadAdsListener listener, HouseDto houseDto);

        interface OnRegisterAd {
            void onRegisterAdSuccess (AdInDto adInDto);
            void onRegisterError (String error);
        }

        void registerAd (OnRegisterAd listener, AdInDto adInDto);
    }

    interface Presenter {

        void loadAllAdsHouse (HouseDto houseDto);

        void registerAd (AdInDto adInDto);
    }

    interface View {

        void showAds(List<Ad> ads);
        void showMessage(String message);

        void showMessageRegister (String message);
        void showErrorAdd (String error);
    }
}
