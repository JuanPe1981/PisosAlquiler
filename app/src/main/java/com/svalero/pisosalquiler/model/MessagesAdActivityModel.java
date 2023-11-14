package com.svalero.pisosalquiler.model;

import android.content.Context;

import com.svalero.pisosalquiler.api.TodoApi;
import com.svalero.pisosalquiler.api.TodoApiInterface;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;

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
    public void getAllMessagesAd (OnLoadMessagesListener listener, AdDto adDto) {
        TodoApiInterface todoApi = TodoApi.buildInstance();
        Call<List<MessageDto>> callMessagesDto = todoApi.getMessagesDto();
        callMessagesDto.enqueue(new Callback<List<MessageDto>>() {
            @Override
            public void onResponse(Call<List<MessageDto>> call, Response<List<MessageDto>> response) {
                List<MessageDto> messagesDtoAd = new ArrayList<>();
                List<MessageDto> messagesDto = response.body();
                for (MessageDto messageDto : messagesDto) {
                    if(messageDto.getIdAd() == adDto.getIdAd()) {
                        messagesDtoAd.add(messageDto);
                    }
                }
                listener.onLoadMessagesSuccess(messagesDtoAd);
            }

            @Override
            public void onFailure(Call<List<MessageDto>> call, Throwable t) {
                String message = "Error called API";
                listener.onLoadMessagesError(message);
            }
        });
    }
}
