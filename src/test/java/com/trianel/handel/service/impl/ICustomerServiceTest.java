package com.trianel.handel.service.impl;

import com.trianel.handel.TrianelHandelApplication;
import com.trianel.handel.model.dto.CustomerDto;
import com.trianel.handel.model.utility.Address;
import com.trianel.handel.model.utility.Gender;
import com.trianel.handel.service.ITrianelService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {TrianelHandelApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ICustomerServiceTest {

    @Autowired
    private ITrianelService<CustomerDto> service;
    private CustomerDto customerDto;

    @BeforeAll
    void setUp() {
        Address address = Address
                .builder()
                .city("Merzenich")
                .country("Germany")
                .street("Mühlenstraße 26")
                .build();

        customerDto =  CustomerDto
                .builder()
                .address(address)
                .lastname("Saliou")
                .firstname("Condé")
                .gender(Gender.MALE)
                .email("saliou-conde@gmx.de")
                .build();
    }

    @Test
    void save() {
        //Given
        String email = "saliou-conde@gmx.de";

        //When
        CustomerDto dto = service.add(customerDto);

        //Then
        assertThat(email).isEqualTo(dto.getEmail());
    }

    @Test
    void getAllStudents() {
    }
}