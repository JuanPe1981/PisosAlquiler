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
import com.svalero.pisosalquiler.contract.MainActivityContract;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MainActivityPresenter;

public class MainActivityView extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
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
            Snackbar.make(((EditText) findViewById(R.id.etUser)), "Nombre o usuario incorrectos", BaseTransientBottomBar.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(MainActivityView.this, MenuActivityView.class);
            //intent.putExtra("user_name", user.getUserName());
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    @Override
    public void showError (String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.etUser)),
                errorMessage, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}