package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.House;

public interface DetailHouseContract {

    interface Model {
        interface OnGetDetailHouseListener {
            void onGetDetailHouseSuccess(House house);
            void onGetDetailHouseError();
        }

        void getHouse (String idHouse, OnGetDetailHouseListener listener);

    }

    interface Presenter {
        void loadHouse (String idHouse);
    }

    interface View {
        void showHouse (House house);
        void showError ();
    }
}
