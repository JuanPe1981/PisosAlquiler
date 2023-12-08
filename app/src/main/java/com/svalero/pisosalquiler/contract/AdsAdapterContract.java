package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;

public interface AdsAdapterContract {

    interface Model {
        interface OnUpdateAdState {
            void onUpdateAdSuccess (AdPatchDto adPatchDto);
            void onUpdateAdError ();
            }

        void updateAdState (OnUpdateAdState listener, long idAd ,AdPatchDto adPatchDto);

    }

    interface Presenter {
        void updateAdState (long idAd, AdPatchDto adPatchDto);
    }

    interface View {
        void showUpdateMessage (String messageSuccess);
        void showUpdateError ();
    }
}
