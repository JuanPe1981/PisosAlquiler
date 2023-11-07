package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.svalero.pisosalquiler.R;

public class MenuActivityView extends AppCompatActivity {

    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);

        tvUserName = (TextView) findViewById(R.id.tvUserName);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user_name");

        tvUserName.setText(user);


    }
}