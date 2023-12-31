package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.User;

public interface MainActivityContract {

    interface Model {
        interface OnGetLoginListener {
            void onGetLoginSuccess(User user);
            void onGetLoginError();
        }
        void getLogin(String userName, String password, OnGetLoginListener listener);

    }

    interface View {
        void showUserLogin (User user);
        void showError ();
    }

    interface Presenter {
        void loginUser (String user, String pass);

    }
}
