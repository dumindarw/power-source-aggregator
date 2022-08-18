package com.vpp.psa.model;

import lombok.Data;

@Data
public class ReturnObject<Object> {

     private boolean isSuccess;
     private Object data;

}
