package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.model.MessagesAdActivityModel;
import com.svalero.pisosalquiler.view.MessagesAdActivityView;

import java.util.List;

public class MessagesAdActivityPresenter implements MessagesAdActivityContract.Presenter,
    MessagesAdActivityContract.Model.OnLoadMessagesListener {

    private MessagesAdActivityModel model;

    private MessagesAdActivityView view;

    public MessagesAdActivityPresenter (MessagesAdActivityView view) {
        this.model = new MessagesAdActivityModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadAllMessagesAd (AdDto adDto) {
        model.getAllMessagesAd(this, adDto);
    }

    @Override
    public void onLoadMessagesSuccess (List<MessageDto> messagesDto) {
        view.showMessages(messagesDto);
    }

    public void onLoadMessagesError (String message) {
        view.showMessageError(message);
    }
}
