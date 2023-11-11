package com.svalero.pisosalquiler.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class House {
    private long idHouse;
    private String addressHouse;
    private int postalCodeHouse;
    private String cityHouse;
    private double latitudeHouse;
    private double longitudeHouse;
    private long userAgencyId;
    private User userProprietaryId;
    private User userRenterId;
}
