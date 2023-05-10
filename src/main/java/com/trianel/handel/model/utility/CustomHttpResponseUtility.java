package com.trianel.handel.model.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class CustomHttpResponseUtility {

    private static CustomHttpResponseUtility instance;

    private CustomHttpResponseUtility() {

    }

    public static CustomHttpResponseUtility getInstance() {
        if(instance == null) {
            instance = new CustomHttpResponseUtility();
        }
        return instance;
    }

    public ResponseEntity<CustomHttpResponse> customHttpResponse(HttpStatus httpStatus, String message) {

        CustomHttpResponse body = CustomHttpResponse
                .builder()
                .message(message)
                .httpStatusCode(httpStatus.value())
                .httpStatus(httpStatus)
                .reason(httpStatus.getReasonPhrase())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(body, httpStatus);
    }
}
