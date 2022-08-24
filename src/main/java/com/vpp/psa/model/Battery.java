package com.vpp.psa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Battery {

    //@JsonIgnore
    //private int id;
    private String name;
    private int postcode;
    @JsonProperty("capacity")
    private double wattCapacity;

    /*public Battery(String name, int postcode, double wattCapacity) {
        this.name = name;
        this.postcode = postcode;
        this.wattCapacity = wattCapacity;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public double getWattCapacity() {
        return wattCapacity;
    }

    public void setWattCapacity(double wattCapacity) {
        this.wattCapacity = wattCapacity;
    }
}


