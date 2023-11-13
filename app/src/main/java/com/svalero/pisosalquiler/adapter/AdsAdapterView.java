package com.svalero.pisosalquiler.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.view.MessagesAdActivityView;

import java.util.List;

public class AdsAdapterView extends RecyclerView.Adapter<AdsAdapterView.MenuHoder>{

    private Context context;

    private List<AdDto> adsList;

    private View snackBarView;


    public AdsAdapterView (Context context, List<AdDto> dataList) {
        this.context = context;
        this.adsList = dataList;
    }

    public Context getContext() {
        return context;
    }


    @Override
    public MenuHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ad_item, parent, false);
        return new MenuHoder(view);
    }

    @Override
    public void onBindViewHolder(MenuHoder holder, int position) {
        holder.titleAd.setText(adsList.get(position).getTitleAd());
        holder.descriptionAd.setText(adsList.get(position).getDescriptionAd());
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }


    public class MenuHoder extends RecyclerView.ViewHolder {

        public TextView titleAd;

        public TextView descriptionAd;

        public View parentView;

        private Button messagesAd;

        private Switch adFinished;


        public MenuHoder( View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            titleAd = view.findViewById(R.id.tvTitleAd);
            descriptionAd = view.findViewById(R.id.tvDescriptionAd);
            messagesAd = view.findViewById(R.id.btMessagesAd);
            adFinished = view.findViewById(R.id.swFinished);

            messagesAd.setOnClickListener(v -> lookMessagesAd(getAdapterPosition()));

        }

        private void lookMessagesAd (int position) {
            AdDto adDto = adsList.get(position);

            Intent intent = new Intent(context, MessagesAdActivityView.class);
            intent.putExtra("idAd", Long.toString(adDto.getIdAd()));
            context.startActivity(intent);
        }




    }
}
