package com.vpp.psa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class Battery {

    private int id;
    private String name;
    private int postcode;
    private double wattCapacity;

}


