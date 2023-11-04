package com.svalero.pisosalquiler.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private long idMessage;
    private String message;
    private LocalDate dateMessage;
    private LocalTime timeMessage;
    private User user;
    private Ad ad;
}
