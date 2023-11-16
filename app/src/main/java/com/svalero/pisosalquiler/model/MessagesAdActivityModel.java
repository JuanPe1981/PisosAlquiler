package com.svalero.pisosalquiler.model;

import android.content.Context;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesAdActivityModel implements MessagesAdActivityContract.Model {

    private Context context;

    public MessagesAdActivityModel (Context context) {
        this.context = context;
    }

    @Override
    public void getAllMessagesAd (OnLoadMessagesListener listener, Ad ad) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<Message>> callMessages = todoApi.getMessages();
        callMessages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messagesAd = new ArrayList<>();
                List<Message> messages = response.body();
                for (Message message : messages) {
                    if(message.getAd().getIdAd() == ad.getIdAd()) {
                        messagesAd.add(message);
                    }
                }
                listener.onLoadMessagesSuccess(messagesAd);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                String message = "Error called API";
                listener.onLoadMessagesError(message);
            }
        });
    }

    @Override
    public void registerMessage (OnRegisterMessage listener, MessageInDto messageInDto) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<MessageInDto> callMessage = todoApi.addMessage(messageInDto);
        callMessage.enqueue(new Callback<MessageInDto>() {
            @Override
            public void onResponse(Call<MessageInDto> call, Response<MessageInDto> response) {
                MessageInDto messageInDto = response.body();
                listener.onRegisterMessageSuccess(messageInDto);
            }
            @Override
            public void onFailure(Call<MessageInDto> call, Throwable t) {
                String error = "Error al llamar a la API";
                listener.onRegisterMessageError(error);
            }
        });
    }
}
