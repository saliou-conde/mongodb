package de.arag.mongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.arag.mongodb.model.dto.CustomerDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
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
