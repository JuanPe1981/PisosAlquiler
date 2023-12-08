package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;

import java.util.List;

public interface MenuActivityContract {

    interface Model {
        interface OnLoadHousesListener {
            void onLoadHousesSuccess(List<HouseDto> housesDto);
            void onLoadHousesError();
        }
        void getAll(OnLoadHousesListener listener, User user);

    }

    interface Presenter {
        void loadAllHouses(User user);
    }

    interface View {
        void showHouses(List<HouseDto> housesDto);
        void showMessage();
    }
}
