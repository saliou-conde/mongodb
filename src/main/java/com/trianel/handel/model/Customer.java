package com.trianel.handel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trianel.handel.model.utility.Gender;
import com.trianel.handel.model.utility.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String customerId;
    private String firstname;
    private String lastname;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private Boolean active;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer age;

}
