package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.adapter.MessagesAdAdapterView;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MessagesAdActivityPresenter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessagesAdActivityView extends AppCompatActivity implements MessagesAdActivityContract.View {

    private String idAd;
    private User user;
    private HouseDto houseDto;
    private Ad ad;
    private List<Message> messagesList;
    private MessagesAdAdapterView adapter;
    private MessagesAdActivityPresenter presenter;
    private Bundle bundle;
    private TextView adTitle;
    private TextView adDescription;
    private TextView dataStart;
    private TextView dataEnd;

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
        ad = (Ad)bundle.getSerializable("ad");


        adTitle = (TextView) findViewById(R.id.tvAdTitle);
        adTitle.setText(ad.getTitleAd());
        adDescription = (TextView) findViewById(R.id.tvAdDescription);
        adDescription.setText(ad.getDescriptionAd());
        dataStart = (TextView) findViewById(R.id.tvDateStart);
        dataStart.setText(ad.getStartDateAd());
        dataEnd = (TextView) findViewById(R.id.tvDateEnd);
        dataEnd.setText(ad.getEndDateAd());


        initializeMessagesAdActivityView(ad, houseDto, user);
    }

    private void initializeMessagesAdActivityView(Ad ad, HouseDto houseDto, User user) {
        messagesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.messages_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MessagesAdAdapterView(this, messagesList, houseDto, ad, user);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllMessagesAd(ad);
    }
    @Override
    public void showMessages(List<Message> messages) {
        messagesList.clear();
        messagesList.addAll(messages);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessageError () {
        Toast.makeText(this, R.string.error_call_API, Toast.LENGTH_LONG).show();
    }

    public void registerMessage (View view) {
        EditText etMessageAd = findViewById(R.id.etMessageAd);

        String message = etMessageAd.getText().toString();
        String dateMessage = LocalDate.now().toString();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeMessage = LocalDateTime.now().toLocalTime().format(format);
        Long user = this.user.getIdUser();
        Long ad = this.ad.getIdAd();

        MessageInDto newMessage = new MessageInDto(message,dateMessage,timeMessage,user,ad);

        presenter.registerMessage(newMessage);
    }

    @Override
    public void showMessageRegister (String message) {
        Snackbar.make(((EditText) findViewById(R.id.etMessageAd)),
                message, BaseTransientBottomBar.LENGTH_LONG).show();
        ((EditText) findViewById(R.id.etMessageAd)).getText().clear();
        //makeNotification();
        onResume();
    }

    @Override
    public void showErrorAdd () {
        Snackbar.make(((EditText) findViewById(R.id.etMessageAd)),
                R.string.error_call_API, BaseTransientBottomBar.LENGTH_LONG).show();
    }

//    public void makeNotification() {
//        String channelId = "CHANNEL_ID_NOTIFICATION";
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(MessagesAdActivityView.this, channelId)
//                .setSmallIcon(R.drawable.logo_transparente)
//                .setContentTitle("Nuevo mensaje")
//                .setContentText("El mensaje escrito.....")
//                .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        Intent intent = new Intent(getApplicationContext(), MessagesAdActivityView.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
//        builder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel =
//                    notificationManager.getNotificationChannel(channelId);
//            if (notificationChannel == null) {
//                int importance = NotificationManager.IMPORTANCE_HIGH;
//                notificationChannel = new NotificationChannel(channelId,
//                        "Some description", importance);
//                notificationChannel.setLightColor(Color.GREEN);
//                notificationChannel.enableVibration(true);
//                notificationManager.createNotificationChannel(notificationChannel);
//            }
//        }
//
//        notificationManager.notify(0, builder.build());
//    }
}