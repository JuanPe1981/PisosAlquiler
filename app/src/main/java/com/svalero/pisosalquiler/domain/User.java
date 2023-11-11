package com.svalero.pisosalquiler.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long idUser;
    private String userName;
    private String password;
    private String nameUser;
    private String firstName;
    private String addressUser;
    private String phoneNumber;
    private List<Ad> ads;
    private List<Message> messages;

}
