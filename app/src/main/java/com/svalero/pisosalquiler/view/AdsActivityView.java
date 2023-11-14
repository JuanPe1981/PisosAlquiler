package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.AdsAdapterView;
import com.svalero.pisosalquiler.contract.AdsActivityContract;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.AdsActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdsActivityView extends AppCompatActivity implements AdsActivityContract.View {

    private User user;

    private String idHouse;

    private List<AdDto> adsList;

    private AdsAdapterView adapter;

    private AdsActivityPresenter presenter;

    private Bundle bundle;

    private HouseDto houseDto;



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
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllAdsHouse(houseDto);
    }

    @Override
    public void showAds(List<AdDto> adsDto) {
        adsList.clear();
        adsList.addAll(adsDto);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}