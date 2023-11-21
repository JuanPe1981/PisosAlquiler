package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.AdsAdapterView;
import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdInDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.AdsActivityPresenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdsActivityView extends AppCompatActivity implements AdsActivityContract.View,
    CompoundButton.OnCheckedChangeListener{

    private User user;

    private String idHouse;

    private List<Ad> adsList;

    private AdsAdapterView adapter;

    private AdsActivityPresenter presenter;

    private Bundle bundle;

    private HouseDto houseDto;

    private Switch viewAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        presenter = new AdsActivityPresenter(this);

        Intent intent = getIntent();
        idHouse = intent.getStringExtra("idHouse");
        bundle = getIntent().getExtras();
        houseDto = (HouseDto)bundle.getSerializable("houseDto");
        user = (User)bundle.getSerializable("user");

        initializeAdsActivityView(houseDto, user);
    }

    private void initializeAdsActivityView(HouseDto houseDto, User user) {
        adsList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.Ads_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdsAdapterView(this, adsList, houseDto, user);
        recyclerView.setAdapter(adapter);

        viewAll = findViewById(R.id.swViewAll);
        viewAll.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllAdsHouse(houseDto, viewAll);
    }

    @Override
    public void showAds(List<Ad> ads) {
        adsList.clear();
        adsList.addAll(ads);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void registerAd (View view) {
        EditText etTitleAd = findViewById(R.id.etTitleAd);
        EditText etDescription = findViewById(R.id.etDescriptionAd);

        String titleAd = etTitleAd.getText().toString();
        String descriptionAd = etDescription.getText().toString();
        String starDateAd = LocalDate.now().toString();
        String endDateAd = "";
        String finishedAd = Boolean.toString(false);
        Long user = this.user.getIdUser();
        Long house = this.houseDto.getIdHouse();

        AdInDto newAdInDto = new AdInDto(titleAd, descriptionAd, starDateAd, endDateAd, finishedAd, user, house);

        presenter.registerAd(newAdInDto);
    }

    @Override
    public void showMessageRegister(String message) {
        Snackbar.make(((EditText) findViewById(R.id.etTitleAd)),
                message, BaseTransientBottomBar.LENGTH_LONG).show();
        ((EditText) findViewById(R.id.etTitleAd)).getText().clear();
        ((EditText) findViewById(R.id.etDescriptionAd)).getText().clear();
        onResume();
    }

    @Override
    public void showErrorAdd(String error) {
        Snackbar.make(((EditText) findViewById(R.id.etMessageAd)),
                error, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.swViewAll) {
            if (isChecked) {
                showMessage("Ver todos las incidencias");
                onResume();
            } else {
                showMessage("Ver solo incidencias pendientes de cerrar");
                onResume();
            }
        }
    }
}