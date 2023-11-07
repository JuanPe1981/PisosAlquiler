package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.User;

public interface MainActivityContract {

    interface Model {
        interface OnGetLoginListener {
            void onGetLoginSuccess(User user);
            void onGetLoginError(String messege);
        }
        void getLogin(String userName, String password, OnGetLoginListener listener);

    }

    interface View {
        void showUserLogin (User user);
        void showError (String errorMessage);
    }

    interface Presenter {
        void loginUser (String user, String pass);

    }
}
