package com.svalero.pisosalquiler.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ad implements Serializable {
    private long idAd;
    private String titleAd;
    private String descriptionAd;
    private String startDateAd;
    private String endDateAd;
    private Boolean finishedAd;
    private User user;
    private House house;
}