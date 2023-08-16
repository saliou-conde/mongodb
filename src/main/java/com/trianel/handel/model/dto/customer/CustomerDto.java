package com.trianel.handel.model.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trianel.handel.model.utility.Address;
import com.trianel.handel.model.Customer;
import com.trianel.handel.model.utility.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String customerId;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private Gender gender;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean active;
    private Integer age;

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return CustomerDto
                .builder()
                .customerId(customer.getCustomerId())
                .active(customer.getActive())
                .address(customer.getAddress())
                .age(customer.getAge())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .gender(customer.getGender())
                .build();
    }

    public static Customer mapCustomerDtoToCustomer(CustomerDto requestDTO) {
        return Customer
                .builder()
                .customerId(requestDTO.customerId == null ? UUID.randomUUID().toString() : requestDTO.customerId)
                .address(requestDTO.address)
                .email(requestDTO.email)
                .gender(requestDTO.gender)
                .firstname(requestDTO.firstname)
                .lastname(requestDTO.lastname)
                .active(requestDTO.active)
                .password(requestDTO.password)
                .age(requestDTO.age)
                .build();
    }

    public static Customer sanitize(Customer customer) {
        return Customer
                .builder()
                .customerId(customer.getCustomerId())
                .address(customer.getAddress())
                .email("")
                .gender(customer.getGender())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .active(customer.getActive())
                .password(customer.getPassword())
                .age(customer.getAge())
                .build();
    }
}
