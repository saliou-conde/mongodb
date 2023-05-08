package de.arag.mongodb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.arag.mongodb.model.Address;
import de.arag.mongodb.model.Customer;
import de.arag.mongodb.model.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerDto {

    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private Gender gender;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean active;
    private Integer age;

    public static CustomerDto mapStudentToStudentDto(Customer customer) {
        return CustomerDto
                .builder()
                .active(customer.getActive())
                .address(customer.getAddress())
                .age(customer.getAge())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .gender(customer.getGender())
                .build();
    }

    public Customer mapStudentDtoToStudent(CustomerDto requestDTO) {
        Customer customer = Customer
                .builder()
                .id(UUID.randomUUID().toString())
                .address(requestDTO.address)
                .email(requestDTO.email)
                .gender(requestDTO.gender)
                .firstname(requestDTO.firstname)
                .lastname(requestDTO.lastname)
                .active(requestDTO.active)
                .password(requestDTO.password)
                .age(requestDTO.age)
                .build();

        return customer;
    }
}
