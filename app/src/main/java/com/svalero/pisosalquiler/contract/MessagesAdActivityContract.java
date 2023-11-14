package com.svalero.pisosalquiler.contract;

import com.svalero.pisosalquiler.domain.Dto.AdDto;
import com.svalero.pisosalquiler.domain.Dto.MessageDto;

import java.util.List;

public interface MessagesAdActivityContract {

    interface Model {

        interface OnLoadMessagesListener {
            void onLoadMessagesSuccess (List<MessageDto> messagesDto);
            void onLoadMessagesError (String message);
        }

        void getAllMessagesAd (OnLoadMessagesListener listener, AdDto adDto);
    }

    interface Presenter {
        void loadAllMessagesAd (AdDto adDto);
    }

    interface View {
        void showMessages(List<MessageDto> mesagesDto);

        void showMessageError (String message);

    }
}
