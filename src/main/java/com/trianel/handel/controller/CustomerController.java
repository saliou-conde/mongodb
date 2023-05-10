package com.trianel.handel.controller;

import com.trianel.handel.model.dto.CustomerDto;
import com.trianel.handel.model.dto.CustomerLogin;
import com.trianel.handel.model.utility.CustomHttpResponse;
import com.trianel.handel.service.ITrianelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class CustomerController {

    private final ITrianelService<CustomerDto> service;

    @PostMapping
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto requestDTO) {
        return ResponseEntity.ok(service.add(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @PostMapping("/login")
    public ResponseEntity<CustomHttpResponse> authenticate(@RequestBody CustomerLogin login) {
        service.authenticate(login);
        return customHttpResponse(HttpStatus.OK, "Customer authenticated");
    }

    @GetMapping("/age")
    public  ResponseEntity<List<CustomerDto>> findCustomerByAgeBetween(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return ResponseEntity.ok(service.findCustomerByAgeBetween(minAge, maxAge));
    }

    @GetMapping("/search")
    public  ResponseEntity<Page<CustomerDto>> searchCustomer(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.searchCustomer(name, minAge, maxAge, city, pageable));
    }

    @GetMapping("/oldest-customer")
    public ResponseEntity<List<Document>> getOldestCustomerByCity() {
        return ResponseEntity.ok(service.getOldestCustomerByCity());
    }

    private ResponseEntity<CustomHttpResponse> customHttpResponse(HttpStatus httpStatus, String message) {

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
