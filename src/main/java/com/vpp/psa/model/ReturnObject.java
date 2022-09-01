package com.vpp.psa.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReturnObject<Object> {

     private boolean isSuccess;
     private Object data;

}
