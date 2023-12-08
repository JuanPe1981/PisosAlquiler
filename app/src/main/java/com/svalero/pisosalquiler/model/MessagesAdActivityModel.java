package com.svalero.pisosalquiler.model;


import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesAdActivityModel implements MessagesAdActivityContract.Model {

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
                listener.onLoadMessagesError();
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
                listener.onRegisterMessageError();
            }
        });
    }
}
