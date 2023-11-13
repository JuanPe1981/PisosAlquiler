package com.svalero.pisosalquiler.domain.Dto;

import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.domain.User;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

    private long idAd;
    private String titleAd;
    private String descriptionAd;
    private LocalDate starDateAd;
    private LocalDate endDateAd;
    private Boolean finishedAd;
    private long idUser;
    private long idHouse;
}
