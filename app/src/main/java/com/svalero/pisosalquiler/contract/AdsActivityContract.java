package com.svalero.pisosalquiler.contract;

import android.widget.Switch;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;

import java.util.List;

public interface AdsActivityContract {

    interface Model {
        interface OnLoadAdsListener {
            void onLoadAdsSuccess (List<Ad> ads);
            void onLoadAdsError (String message);
        }

        void getAllAdsHouse (OnLoadAdsListener listener, HouseDto houseDto, Switch viewAll);

        interface OnRegisterAd {
            void onRegisterAdSuccess (AdInDto adInDto);
            void onRegisterError (String error);
        }

        void registerAd (OnRegisterAd listener, AdInDto adInDto);
    }

    interface Presenter {

        void loadAllAdsHouse (HouseDto houseDto, Switch viewAll);

        void registerAd (AdInDto adInDto);
    }

    interface View {

        void showAds(List<Ad> ads);
        void showMessage(String message);

        void showMessageRegister (String message);
        void showErrorAdd (String error);
    }
}
