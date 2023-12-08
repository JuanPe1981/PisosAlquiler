package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.MainActivityContract;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MainActivityPresenter;
import com.svalero.pisosalquiler.presenter.MenuActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivityView extends AppCompatActivity implements MainActivityContract.View, MenuActivityContract.View {

    private MainActivityPresenter presenter;

    private MenuActivityPresenter presenterHouse;

    private User registerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenterHouse = new MenuActivityPresenter(this);
    }

    public void setBtLogin(View view){

        TextView etuserName = findViewById(R.id.etUser);
        String userName = etuserName.getText().toString();
        TextView etpassword = findViewById(R.id.etPassword);
        String password = etpassword.getText().toString();

        presenter.loginUser(userName,password);
    }

    @Override
    public void showUserLogin (User user) {
        if (user == null) {
            Snackbar snackbar = Snackbar.make(((EditText)findViewById(R.id.etUser)), R.string.user_pass_error, BaseTransientBottomBar.LENGTH_LONG);
            View view = snackbar.getView();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            snackbar.show();
        } else {
            registerUser = user;
            presenterHouse.loadAllHouses(user);
        }
    }

    @Override
    public void showError () {
        Snackbar snackbar = Snackbar.make(((EditText) findViewById(R.id.etUser)),
                R.string.error_call_API, BaseTransientBottomBar.LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snackbar.show();
    }

    @Override
    public void showHouses(List<HouseDto> housesDto) {
        List housesList = new ArrayList<HouseDto>();
        housesList.clear();
        housesList.addAll(housesDto);

        if (housesList.size() > 1) {
            Intent intent = new Intent(MainActivityView.this, MenuActivityView.class);
            intent.putExtra("user", registerUser);
            startActivity(intent);
        } else if (housesList.size() == 0) {
            Snackbar.make(((EditText) findViewById(R.id.etUser)),
                    R.string.no_house_assigned, BaseTransientBottomBar.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(MainActivityView.this, AdsActivityView.class);
            intent.putExtra("idHouse", Long.toString(housesDto.get(0).getIdHouse()));
            intent.putExtra("houseDto", housesDto.get(0));
            intent.putExtra("user", registerUser);
            startActivity(intent);
        }
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, R.string.error_call_API, Toast.LENGTH_LONG).show();
    }
}