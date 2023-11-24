package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;
import com.svalero.pisosalquiler.domain.User;

public interface UserMenuContract {

    interface Model {
        interface OnUpdatePassword {
            void onUpdatePasswordSuccess (UserPatchDto userPatchDto);

            void onUpdatePasswordError (String errorMessage);
        }

        void updatePassword (OnUpdatePassword listener, long idUser, UserPatchDto userPatchDto);

    }

    interface Presenter {
        void updatePassword (long idUser, UserPatchDto userPatchDto);
    }

    interface View {
        void showUpdateMessage (String messageSuccess);
        void showUpdateError (String messageError);
    }
}
