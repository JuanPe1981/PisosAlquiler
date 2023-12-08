package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;

public interface UserMenuContract {

    interface Model {
        interface OnUpdatePassword {
            void onUpdatePasswordSuccess (UserPatchDto userPatchDto);

            void onUpdatePasswordError ();
        }

        void updatePassword (OnUpdatePassword listener, long idUser, UserPatchDto userPatchDto);

    }

    interface Presenter {
        void updatePassword (long idUser, UserPatchDto userPatchDto);
    }

    interface View {
        void showUpdateMessage (String messageSuccess);
        void showUpdateError ();
    }
}
