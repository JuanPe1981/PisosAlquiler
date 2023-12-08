package com.svalero.pisosalquiler.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {
    private long idMessage;
    private String message;
    private String dateMessage;
    private String timeMessage;
    private User user;
    private Ad ad;

//    public Message(String message, String dateMessage, String timeMessage, User user, Ad ad) {
//        this.message = message;
//        this.dateMessage = dateMessage;
//        this.timeMessage = timeMessage;
//        this.user = user;
//        this.ad = ad;
//    }
}


