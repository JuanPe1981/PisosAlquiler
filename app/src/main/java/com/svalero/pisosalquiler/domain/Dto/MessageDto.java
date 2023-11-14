package com.svalero.pisosalquiler.domain.Dto;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {
    private long idMessage;
    private String message;
    private Date dateMessage;
    private Time timeMessage;
    private long idUser;
    private long idAd;
}
