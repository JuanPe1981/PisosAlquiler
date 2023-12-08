package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.UserMenuContract;
import com.svalero.pisosalquiler.domain.Dto.UserPatchDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.UserMenuPresenter;

public class UserMenuView extends AppCompatActivity implements UserMenuContract.View {

    private String idUser;
    private User user;

    private UserPatchDto userPatchDto;

    private UserMenuPresenter presenter;

    private Bundle bundle;

    private View snackBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu_view);

        presenter = new UserMenuPresenter(this);

        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        user = (User)bundle.getSerializable("user");

        TextView tvNameUser = findViewById(R.id.tvNameUser);
        TextView tvFirstName = findViewById(R.id.tvfirstname);
        TextView tvAddressUser = findViewById(R.id.tvAddressUser);
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        TextView tvUserName = findViewById(R.id.tvUserName);


        tvNameUser.setText(user.getNameUser());
        tvFirstName.setText(user.getFirstName());
        tvAddressUser.setText(user.getAddressUser());
        tvPhoneNumber.setText(user.getPhoneNumber());
        tvUserName.setText(user.getUserName());
    }

    public void changePassword (View view) {
        EditText etPasswordNew = findViewById(R.id.etPasswordNew);
        EditText etRepeatNewPassword = findViewById(R.id.etRepeatNewPassword);

        String passwordNew = etPasswordNew.getText().toString();
        String repeatNewPassword = etRepeatNewPassword.getText().toString();

        if (passwordNew.isEmpty() && repeatNewPassword.isEmpty()) {
            Snackbar.make(((EditText) findViewById(R.id.etPasswordNew)),
                    R.string.two_params_change_pass,
                    BaseTransientBottomBar.LENGTH_LONG).show();
        } else if (!passwordNew.equals(repeatNewPassword)) {
            Snackbar.make(((EditText) findViewById(R.id.etPasswordNew)),
                    R.string.two_new_pass_different,
                    BaseTransientBottomBar.LENGTH_LONG).show();
        } else if (passwordNew.equals(repeatNewPassword)) {
            UserPatchDto newUserPassword = new UserPatchDto();
            newUserPassword.setPassword(passwordNew);

            presenter.updatePassword(user.getIdUser(), newUserPassword);
        }
    }

    @Override
    public void showUpdateMessage(String messageSuccess) {
        Snackbar.make(((EditText) findViewById(R.id.etPasswordNew)),
                messageSuccess,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateError() {
        Snackbar.make(((EditText) findViewById(R.id.etPasswordNew)),
                R.string.error_call_API,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}