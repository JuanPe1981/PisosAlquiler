package com.svalero.pisosalquiler.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar tbads = findViewById(R.id.tbHouse);
        setSupportActionBar(tbads);



        presenter = new AdsActivityPresenter(this);

        Intent intent = getIntent();
        idHouse = intent.getStringExtra("idHouse");
        bundle = getIntent().getExtras();
        houseDto = (HouseDto)bundle.getSerializable("houseDto");
        user = (User)bundle.getSerializable("user");

        initializeAdsActivityView(houseDto, user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.text_type_search));
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.changePassword) {
            Intent intent = new Intent(this, UserMenuView.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else if (id == R.id.closeSesion) {
            Intent intent = new Intent(this, MainActivityView.class);
            startActivity(intent);
            finish();
        }
        return true;
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
    public void showMessage () {
        Toast.makeText(this, R.string.error_call_API, Toast.LENGTH_LONG).show();
    }

    public void registerAd (View view) {
        EditText etTitleAd = findViewById(R.id.etTitleAd);
        EditText etDescription = findViewById(R.id.etDescriptionAd);


        AdInDto newAdInDto = new AdInDto();

        newAdInDto.setUser(this.user.getIdUser());
        newAdInDto.setHouse(this.houseDto.getIdHouse());
        String titleAd = etTitleAd.getText().toString();
        newAdInDto.setTitleAd(titleAd);
        String descriptionAd = etDescription.getText().toString();
        newAdInDto.setDescriptionAd(descriptionAd);
        newAdInDto.setStartDateAd(LocalDate.now().toString());
        newAdInDto.setEndDateAd("");
        newAdInDto.setFinishedAd("false");


        presenter.registerAd(newAdInDto);
    }

    @Override
    public void showMessageRegister() {
        Snackbar.make(((EditText) findViewById(R.id.etTitleAd)),
                R.string.add_ad_success, BaseTransientBottomBar.LENGTH_LONG).show();
        ((EditText) findViewById(R.id.etTitleAd)).getText().clear();
        ((EditText) findViewById(R.id.etDescriptionAd)).getText().clear();
        onResume();
    }

    @Override
    public void showErrorAdd() {
        Snackbar.make(((EditText) findViewById(R.id.etMessageAd)),
                R.string.error_call_API, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.swViewAll) {
            if (isChecked) {
                showMessageCheckedCanged(getString(R.string.show_all_ads));
                onResume();
            } else {
                showMessageCheckedCanged(getString(R.string.show_unclosed_ads));
                onResume();
            }
        }
    }

    public void showMessageCheckedCanged (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void filterList (String text) {
        List<Ad> filteredList = new ArrayList<>();
        for (Ad ad : adsList) {
            if (ad.getTitleAd().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(ad);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, R.string.no_data_found, Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFiltered(filteredList);
        }
    }
}