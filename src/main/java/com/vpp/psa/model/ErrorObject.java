package com.vpp.psa.model;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ErrorObject {

    private int code;
    private String message;
    private String type;
    private String time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));

    public ErrorObject(int code, String message, String type){
        this.code =  code;
        this.message = message;
        this.type = type;
    }

    public static ErrorObjectBuilder builder(){
        return new ErrorObjectBuilder();
    }

    public static class ErrorObjectBuilder {

        private int code;
        private String message;
        private String type;

        public ErrorObjectBuilder withCode(int code) {
            this.code = code;
            return this;
        }

        public ErrorObjectBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorObjectBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public ErrorObject build(){
            return new ErrorObject(this.code, this.message, this.type);
        }
    }
}
