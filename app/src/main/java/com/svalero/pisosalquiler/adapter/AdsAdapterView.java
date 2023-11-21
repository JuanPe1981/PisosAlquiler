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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.AdsAdapterContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdPatchDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.AdsAdapterPresenter;
import com.svalero.pisosalquiler.view.MessagesAdActivityView;

import java.time.LocalDate;
import java.util.List;

public class AdsAdapterView extends RecyclerView.Adapter<AdsAdapterView.MenuHoder>
    implements AdsAdapterContract.View {

    private User user;

    private HouseDto houseDto;
    private Context context;

    private List<Ad> adsList;

    private View snackBarView;

    private AdsAdapterPresenter presenter;


    public AdsAdapterView (Context context, List<Ad> dataList, HouseDto houseDto, User user) {
        this.context = context;
        this.adsList = dataList;
        this.houseDto = houseDto;
        this.user = user;
        presenter = new AdsAdapterPresenter(this);
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
        holder.adFinished.setChecked(adsList.get(position).getFinishedAd());
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }

    @Override
    public void showUpdateMessage(String messageSuccess) {
        Snackbar.make(snackBarView, messageSuccess,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showUpdateError(String messageError) {
        Snackbar.make(snackBarView, messageError,
                BaseTransientBottomBar.LENGTH_LONG).show();
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
            adFinished.setOnClickListener(v -> setAdFinished(getAdapterPosition()));

        }

        private void lookMessagesAd (int position) {
            Ad ad = adsList.get(position);

            Intent intent = new Intent(context, MessagesAdActivityView.class);
            intent.putExtra("idAd", Long.toString(ad.getIdAd()));
            intent.putExtra("ad", ad);
            intent.putExtra("houseDto", houseDto);
            intent.putExtra("user", user);
            context.startActivity(intent);
        }

        private void setAdFinished (int position) {
            Ad ad = adsList.get(position);

            AdPatchDto adPatchDto = new AdPatchDto();
            if (ad.getFinishedAd().equals(false)) {
                adPatchDto.setFinishedAd(true);
                ad.setFinishedAd(true);
                adPatchDto.setEndDateAd(LocalDate.now().toString());
            } else {
                adPatchDto.setFinishedAd(false);
                ad.setFinishedAd(false);
                adPatchDto.setEndDateAd("");
            }

            presenter.updateAdState(ad.getIdAd(), adPatchDto);

            notifyItemChanged(position);
        }

    }
}
