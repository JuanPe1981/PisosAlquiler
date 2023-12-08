package com.svalero.pisosalquiler.presenter;

import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.MessagesAdActivityContract;
import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;
import com.svalero.pisosalquiler.model.MessagesAdActivityModel;
import com.svalero.pisosalquiler.view.MessagesAdActivityView;

import java.util.List;

public class MessagesAdActivityPresenter implements MessagesAdActivityContract.Presenter,
    MessagesAdActivityContract.Model.OnLoadMessagesListener,
    MessagesAdActivityContract.Model.OnRegisterMessage {

    private MessagesAdActivityModel model;

    private MessagesAdActivityView view;

    public MessagesAdActivityPresenter (MessagesAdActivityView view) {
        this.model = new MessagesAdActivityModel();
        this.view = view;
    }

    @Override
    public void loadAllMessagesAd (Ad ad) {
        model.getAllMessagesAd(this, ad);
    }

    @Override
    public void onLoadMessagesSuccess (List<Message> messages) {
        view.showMessages(messages);
    }

    @Override
    public void onLoadMessagesError () {
        view.showMessageError();
    }

    @Override
    public void registerMessage (MessageInDto messageInDto) {
        model.registerMessage(this, messageInDto);
    }

    @Override
    public void onRegisterMessageSuccess(MessageInDto messageInDto) {
        view.showMessageRegister(view.getString(R.string.add_message_success));
    }

    @Override
    public void onRegisterMessageError() {
        view.showErrorAdd();
    }
}
