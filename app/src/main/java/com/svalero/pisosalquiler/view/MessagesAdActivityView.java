package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.MessagesAdAdapterView;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MessagesAdActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdActivityView extends AppCompatActivity implements MessagesAdActivityContract.View {

    private String idAd;
    private User user;
    private HouseDto houseDto;
    private AdDto adDto;
    private List<MessageDto> messagesList;
    private MessagesAdAdapterView adapter;
    private MessagesAdActivityPresenter presenter;
    private Bundle bundle;
    private MessageDto messageDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_ad_view);

        presenter = new MessagesAdActivityPresenter(this);

        Intent intent = getIntent();
        idAd = intent.getStringExtra("idHouse");
        bundle = getIntent().getExtras();
        user = (User)bundle.getSerializable("user");
        houseDto = (HouseDto)bundle.getSerializable("houseDto");
        adDto = (AdDto)bundle.getSerializable("AdDto");

        initializeMessagesAdActivityView(adDto, houseDto, user);
    }

    private void initializeMessagesAdActivityView(AdDto adDto, HouseDto houseDto, User user) {
        messagesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.messages_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MessagesAdAdapterView(this, messagesList, houseDto, adDto, user);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllMessagesAd(adDto);
    }
    @Override
    public void showMessages(List<MessageDto> messagesDto) {
        messagesList.clear();
        messagesList.addAll(messagesDto);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessageError (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}