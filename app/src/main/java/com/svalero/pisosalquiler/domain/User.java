package com.svalero.pisosalquiler.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private long idUser;
    private String userName;
    private String password;
    private String nameUser;
    private String firstName;
    private String addressUser;
    private String phoneNumber;
    private Boolean admin;
    private List<Ad> ads;
    private List<Message> messages;
    private List<House> housesAgency;
    private List<House> housesProprietary;
    private List<House> housesRenter;


}
