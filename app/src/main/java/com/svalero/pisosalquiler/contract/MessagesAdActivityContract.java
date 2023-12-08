package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Ad;
import com.svalero.pisosalquiler.domain.Dto.MessageInDto;
import com.svalero.pisosalquiler.domain.Message;

import java.util.List;

public interface MessagesAdActivityContract {

    interface Model {

        interface OnLoadMessagesListener {
            void onLoadMessagesSuccess (List<Message> messages);
            void onLoadMessagesError ();
        }
        void getAllMessagesAd (OnLoadMessagesListener listener, Ad ad);

        interface OnRegisterMessage {
            void onRegisterMessageSuccess (MessageInDto messageInDto);
            void onRegisterMessageError ();
        }
        void registerMessage(OnRegisterMessage listener, MessageInDto messageInDto);
    }

    interface Presenter {
        void loadAllMessagesAd (Ad ad);
        void registerMessage (MessageInDto messageInDto);
    }

    interface View {
        void showMessages(List<Message> messages);
        void showMessageError ();
        void showMessageRegister (String message);

        void showErrorAdd ();

    }
}
