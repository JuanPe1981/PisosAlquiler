package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.MenuAdapterView;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.presenter.MenuActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivityView extends AppCompatActivity implements MenuActivityContract.View {

    private String userName;
    private TextView tvHouses;

    private Button btDetailHouse;

    private Button btAdsHouse;
    private List<House> housesList;
    private MenuAdapterView adapter;
    private MenuActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);

        presenter = new MenuActivityPresenter(this);

        initializeMenuActivityView();

        Intent intent = getIntent();
        userName = intent.getStringExtra("user_name");

    }

    private void initializeMenuActivityView() {
        housesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.House_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MenuAdapterView(this, housesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllHouses();
    }


    @Override
    public void showHouses(List<House> houses) {
        housesList.clear();
        housesList.addAll(houses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}