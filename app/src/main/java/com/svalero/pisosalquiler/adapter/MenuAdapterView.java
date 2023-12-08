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
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.view.AdsActivityView;
import com.svalero.pisosalquiler.view.DetailHouseView;

import java.util.List;

public class MenuAdapterView extends RecyclerView.Adapter<MenuAdapterView.MenuHolder> {

    private User user;
    private Context context;
    private List<HouseDto> housesList;
    private View snackBarView;

    public void setFiltered (List<HouseDto> filteredList) {
        this.housesList = filteredList;
        notifyDataSetChanged();
    }


    public MenuAdapterView(Context context, List<HouseDto> dataList, User user) {
        this.context = context;
        this.housesList = dataList;
        this.user = user;
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
        holder.postalCode.setText(Integer.toString(housesList.get(position).getPostalCodeHouse()));
        holder.city.setText(housesList.get(position).getCityHouse());
    }

    @Override
    public int getItemCount() {
        return housesList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public TextView addressHouse;
        public TextView idHouse;
        public TextView postalCode;
        public TextView city;
        public View parentView;
        private Button detailHouse;
        private Button adsHouse;
        public MenuHolder(View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            addressHouse = view.findViewById(R.id.tvAddress);
            detailHouse = view.findViewById(R.id.btDetailHouse);
            postalCode = view.findViewById(R.id.tvPostalCode);
            city = view.findViewById(R.id.tvCity);

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

