package com.svalero.pisosalquiler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.Message;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;

import java.util.List;

public class MessagesAdAdapterView extends RecyclerView.Adapter<MessagesAdAdapterView.MenuHolder> {

    private User user;
    private HouseDto houseDto;
    private Ad ad;
    private Context context;
    private List<Message> messageList;
    private View snackBarView;

    public MessagesAdAdapterView (Context context, List<Message> dataList, HouseDto houseDto, Ad ad, User user) {
        this.context = context;
        this.messageList = dataList;
        this.houseDto = houseDto;
        this.ad = ad;
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
        holder.userMessage.setText(messageList.get(position).getUser().getUserName());
        holder.messageText.setText(messageList.get(position).getMessage());
        holder.dateMessage.setText(messageList.get(position).getDateMessage());
        holder.timeMessage.setText(messageList.get(position).getTimeMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        public TextView userMessage;
        public TextView messageText;

        public TextView dateMessage;

        public TextView timeMessage;
        public View parentView;

        public MenuHolder( View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            userMessage = view.findViewById(R.id.tvUserMessage);
            messageText = view.findViewById(R.id.tvMessageText);
            dateMessage = view.findViewById(R.id.tvDateMessage);
            timeMessage = view.findViewById(R.id.tvTimeMessage);
        }
    }
}
