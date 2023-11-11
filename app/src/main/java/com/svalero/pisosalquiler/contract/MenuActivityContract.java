package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.House;

import java.util.List;

public interface MenuActivityContract {

    interface Model {
        interface OnLoadHousesListener {
            void onLoadHousesSuccess(List<House> houses);
            void onLoadHousesError(String message);
        }
        void getAll(OnLoadHousesListener listener);

    }

    interface Presenter {
        void loadAllHouses();
    }

    interface View {
        void showHouses(List<House> houses);
        void showMessage(String message);
    }
}
