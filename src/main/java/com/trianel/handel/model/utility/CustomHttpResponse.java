package com.trianel.handel.model.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trianel.handel.model.dto.customer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomHttpResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm.ss", timezone = "Europe/Paris")
    private LocalDateTime timeStamp;
    private int httpStatusCode; //200, 201, 400, 500
    private HttpStatus httpStatus;
    private String reason;
    private String message;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CustomerDto customerDto;
}
