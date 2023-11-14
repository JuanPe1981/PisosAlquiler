package com.svalero.pisosalquiler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;

import java.util.List;

public class MessagesAdAdapterView extends RecyclerView.Adapter<MessagesAdAdapterView.MenuHolder> {

    private User user;
    private HouseDto houseDto;
    private AdDto adDto;
    private Context context;
    private List<MessageDto> messageDtoList;
    private View snackBarView;

    public MessagesAdAdapterView (Context context, List<MessageDto> dataList, HouseDto houseDto, AdDto adDto, User user) {
        this.context = context;
        this.messageDtoList = dataList;
        this.houseDto = houseDto;
        this.adDto = adDto;
        this.user = user;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        holder.userMessage.setText(Long.toString(messageDtoList.get(position).getIdUser()));
        holder.messageText.setText(messageDtoList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messageDtoList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public TextView userMessage;
        public TextView messageText;
        public View parentView;

        public MenuHolder( View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            userMessage = view.findViewById(R.id.tvUserMessage);
            messageText = view.findViewById(R.id.tvMessageText);
        }
    }
}
