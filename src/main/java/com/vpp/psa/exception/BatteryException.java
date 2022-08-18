package com.vpp.psa.exception;

import org.jooq.exception.DataAccessException;

public class BatteryException extends DataAccessException {

    public BatteryException(String message) {
        super(message);
    }

    public BatteryException(String message, Throwable cause) {
        super(message, cause);
    }


}
