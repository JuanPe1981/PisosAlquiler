package com.svalero.pisosalquiler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.MenuAdapterContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MenuAdapterPresenter;
import com.svalero.pisosalquiler.view.AdsActivityView;
import com.svalero.pisosalquiler.view.DetailHouseView;

import java.util.List;

public class MenuAdapterView extends RecyclerView.Adapter<MenuAdapterView.MenuHolder> {

    private User user;
    private Context context;
    private List<HouseDto> housesList;
    private View snackBarView;
    //private MenuAdapterPresenter presenter;


    public MenuAdapterView(Context context, List<HouseDto> dataList, User user) {
        this.context = context;
        this.housesList = dataList;
        this.user = user;
        //presenter = new MenuAdapterPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.house_item, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.addressHouse.setText(housesList.get(position).getAddressHouse());
        holder.idHouse.setText(Long.toString(housesList.get(position).getIdHouse()));
    }

    @Override
    public int getItemCount() {
        return housesList.size();
    }

//    @Override
//    public void showMessage(String message) {
//        Snackbar.make(snackBarView, message,
//                BaseTransientBottomBar.LENGTH_LONG).show();
//    }

//    @Override
//    public void showError(String errorMessage) {
//        Snackbar.make(snackBarView, errorMessage,
//                BaseTransientBottomBar.LENGTH_LONG).show();
//    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public TextView addressHouse;
        public TextView idHouse;
        public View parentView;

        private Button detailHouse;

        private Button adsHouse;
        public MenuHolder(View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            addressHouse = view.findViewById(R.id.tvAddress);
            idHouse = view.findViewById(R.id.tvIdHouse);
            detailHouse = view.findViewById(R.id.btDetailHouse);
            adsHouse = view.findViewById(R.id.btAdsHouse);

            detailHouse.setOnClickListener(v -> lookDetailsHouse(getAdapterPosition()));
            adsHouse.setOnClickListener(v -> lookAdsHouse(getAdapterPosition()));
        }
    }

    private void lookDetailsHouse(int position) {
        HouseDto houseDto = housesList.get(position);

        Intent intent = new Intent(context, DetailHouseView.class);
        intent.putExtra("idHouse", Long.toString(houseDto.getIdHouse()));
        context.startActivity(intent);
    }

    private void lookAdsHouse (int position) {
        HouseDto houseDto = housesList.get(position);

        Intent intent = new Intent(context, AdsActivityView.class);
        intent.putExtra("idHouse", Long.toString(houseDto.getIdHouse()));
        intent.putExtra("houseDto", houseDto);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }
}

