package com.svalero.pisosalquiler.domain.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdInDto {

    private String titleAd;
    private String descriptionAd;
    private String startDateAd;
    private String endDateAd;
    private String finishedAd;

    private long user;

    private long house;
}
