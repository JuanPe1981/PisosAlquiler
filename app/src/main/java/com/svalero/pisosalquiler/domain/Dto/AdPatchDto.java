package com.svalero.pisosalquiler.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPatchDto {
    private Boolean finishedAd;
    private String endDateAd;
}
