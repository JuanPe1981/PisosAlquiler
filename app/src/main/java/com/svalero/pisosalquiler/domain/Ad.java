package com.svalero.pisosalquiler.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ad {
    private long idAd;
    private String titleAd;
    private String descriptionAd;
    private LocalDate starDateAd;
    private LocalDate endDateAd;
    private Boolean finishedAd;
    private User user;
    private House house;
}
