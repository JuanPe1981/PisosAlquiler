package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;

import java.util.List;

public interface MessagesAdActivityContract {

    interface Model {

        interface OnLoadMessagesListener {
            void onLoadMessagesSuccess (List<Message> messages);
            void onLoadMessagesError (String message);
        }
        void getAllMessagesAd (OnLoadMessagesListener listener, Ad ad);

        interface OnRegisterMessage {
            void onRegisterMessageSuccess (MessageInDto messageInDto);
            void onRegisterMessageError (String error);
        }
        void registerMessage(OnRegisterMessage listener, MessageInDto messageInDto);
    }

    interface Presenter {
        void loadAllMessagesAd (Ad ad);
        void registerMessage (MessageInDto messageInDto);
    }

    interface View {
        void showMessages(List<Message> messages);
        void showMessageError (String message);
        void showMessageRegister (String message);

        void showErrorAdd (String error);

    }
}
