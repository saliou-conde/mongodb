package de.arag.mongodb.service;

import de.arag.mongodb.model.dto.CustomerLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public interface ICustomerService<T> {

    T add(T student);

    List<T> getAllCustomers();

    T authenticate(CustomerLogin login);

    List<T> findCustomerByAgeBetween(Integer minAge, Integer maxAge);

    Page<T> searchCustomer(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);

    List<Document> getOldestCustomerByCity();
}