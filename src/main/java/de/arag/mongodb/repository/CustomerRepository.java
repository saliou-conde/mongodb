package de.arag.mongodb.repository;

import de.arag.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findStudentByEmail(String email);

    //List<Customer> findCustomerByAgeBetween(Integer minAge, Integer maxAge);

    @Query(value = "{'age' : {$gt : ?0, $lt : ?1}}",
    fields = "{address: 0}")
    List<Customer> findCustomerByAgeBetween(Integer minAge, Integer maxAge);
}
