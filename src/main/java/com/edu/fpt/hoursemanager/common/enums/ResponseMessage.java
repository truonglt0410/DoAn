package com.edu.fpt.hoursemanager.common.enums;

import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    SUCCESSFUL("HM200","Successful"),
    CREATED("HM201","Created"),
    BAD_REQUEST("HM400","Bad Request"),
    UNAUTHORIZED("HM401","Unauthorized"),
    FORBIDDEN("HM403","Forbidden"),
    NOT_FOUND("HM404","Not Found"),
    METHOD_NOT_ALLOWED("HM405","Method Not Allowed"),
    REQUEST_TIMEOUT("HM408","Request Timeout"),
    CONFLICT("HM409","Conflict"),
    INTERNAL_SERVER_ERROR("HM500","Internal Server Error"),
    BAD_GATEWAY("HM502","Bad Gateway"),
    SERVICE_UNAVAILABLE("HM503","Service Unavailable"),
    GATEWAY_TIMEOUT("HM504","Gateway Timeout");

    private final String code;
    private final String message;

    public static ResponseMessage lookUp(String code) throws HouseManagerExceptions {
        return Arrays.stream(ResponseMessage.values())
                .filter(responseMeaagse -> responseMeaagse.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new HouseManagerExceptions("Not found provider account" + code));
    }
}
