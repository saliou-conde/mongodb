package com.trianel.handel.controller;

import com.trianel.handel.model.dto.CustomerDto;
import com.trianel.handel.model.dto.LoginDto;
import com.trianel.handel.model.utility.CustomHttpResponse;
import com.trianel.handel.model.utility.CustomHttpResponseUtility;
import com.trianel.handel.service.ITrianelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ITrianelService<CustomerDto> service;

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto requestDTO) {
        return ResponseEntity.ok(service.addEntity(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllEntities());
    }

    @PostMapping("/login")
    public ResponseEntity<CustomHttpResponse> authenticateCustomer(@RequestBody LoginDto login) {
        service.authenticate(login);
        return CustomHttpResponseUtility.getInstance().customHttpResponse(HttpStatus.OK, "Customer Successfully authenticated");
    }

    @GetMapping("/search")
    public  ResponseEntity<Page<CustomerDto>> searchCustomers(
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

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(service.deleteEntity(customerId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto requestDTO) {
        return ResponseEntity.ok(service.updateEntity(customerId, requestDTO));
    }
}
