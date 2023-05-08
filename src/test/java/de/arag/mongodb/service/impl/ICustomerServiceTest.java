package de.arag.mongodb.service.impl;

import de.arag.mongodb.MongodbApplication;
import de.arag.mongodb.model.Address;
import de.arag.mongodb.model.Gender;
import de.arag.mongodb.model.dto.CustomerDto;
import de.arag.mongodb.service.ICustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {MongodbApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ICustomerServiceTest {

    @Autowired
    private ICustomerService<CustomerDto> service;
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