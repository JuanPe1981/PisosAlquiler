package com.svalero.pisosalquiler.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class House implements Serializable {
    private long idHouse;
    private String addressHouse;
    private int postalCodeHouse;
    private String cityHouse;
    private double latitudeHouse;
    private double longitudeHouse;
    private User userAgencyId;
    private User userProprietaryId;
    private User userRenterId;

    private List<Ad> ads;

    public long getIdHouse() {
        return idHouse;
    }

}
