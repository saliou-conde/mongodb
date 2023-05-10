package com.trianel.handel.repository;

import com.trianel.handel.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByCustomerId(String customerId);

    @Query(value = "{'age' : {$gt : ?0, $lt : ?1}}",
    fields = "{address: 0}")//Address will be displayed as 'null' object in the json response body
    List<Customer> findCustomerByAgeBetween(Integer minAge, Integer maxAge);
}
