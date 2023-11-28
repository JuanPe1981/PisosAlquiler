package com.svalero.pisosalquiler.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.MenuAdapterView;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MenuActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivityView extends AppCompatActivity implements MenuActivityContract.View {

    private User user;
    private Bundle bundle;
    private List<HouseDto> housesList;
    private MenuAdapterView adapter;
    private MenuActivityPresenter presenter;

    private ArrayAdapter<String> arrayAdapterHouse;
    private ArrayList<String> direcciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);
        Toolbar tbHouse = findViewById(R.id.tbHouse);
        setSupportActionBar(tbHouse);

        arrayAdapterHouse = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, direcciones);

        presenter = new MenuActivityPresenter(this);

        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        user = (User)bundle.getSerializable("user");


        initializeMenuActivityView(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
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
        } else if (item.getItemId() == R.id.SuperMap) {
            Intent intent = new Intent(this, MapsActivityView.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        return true;
    }

    private void initializeMenuActivityView(User user) {
        housesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.House_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MenuAdapterView(this, housesList, user);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllHouses(user);
    }


    @Override
    public void showHouses(List<HouseDto> housesDto) {
        housesList.clear();
        housesList.addAll(housesDto);
        ArrayList<String> direcciones = new ArrayList<>();
        for (int i=0; i < housesDto.size(); i++) {
            direcciones.add(housesDto.get(i).getAddressHouse());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void filterList (String text) {
        List<HouseDto> filteredList = new ArrayList<>();
        for (HouseDto houseDto : housesList) {
            if (houseDto.getAddressHouse().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(houseDto);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFiltered(filteredList);
        }
    }



}