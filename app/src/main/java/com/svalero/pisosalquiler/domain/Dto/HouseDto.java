package com.svalero.pisosalquiler.domain.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto implements Serializable {

    private long idHouse;
    private String addressHouse;
    private int postalCodeHouse;
    private String cityHouse;
    private double latitudeHouse;
    private double longitudeHouse;
    private long userAgencyId;
    private long userProprietaryId;
    private long userRenterId;

}
