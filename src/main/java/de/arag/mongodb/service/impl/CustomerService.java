package de.arag.mongodb.service.impl;

import de.arag.mongodb.model.Customer;
import de.arag.mongodb.model.dto.CustomerDto;
import de.arag.mongodb.model.dto.CustomerLogin;
import de.arag.mongodb.repository.CustomerRepository;
import de.arag.mongodb.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService<CustomerDto> {

    private final CustomerRepository repository;
    private final MongoTemplate template;

    @Override
    public CustomerDto add(CustomerDto requestDTO) {
        String email = requestDTO.getEmail();
        if(customerExists(email)) {
            throw new IllegalStateException("Email already in use: "+email);
        }
        Customer customer      = requestDTO.mapStudentDtoToStudent(requestDTO);
        ValidationResult apply = CustomerValidator.isCustomerEmailValid().apply(customer);
        if (apply == ValidationResult.VALID) {
            Customer insert = repository.insert(customer);
            return CustomerDto.mapStudentToStudentDto(insert);
        }
        throw new IllegalStateException("Email format not valid: "+email);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mapStudentToStudentDto(repository.findAll());
    }

    @Override
    public List<CustomerDto> findCustomerByAgeBetween(Integer minAge, Integer maxAge) {
        List<CustomerDto> customerDto = mapStudentToStudentDto(repository.findCustomerByAgeBetween(minAge, maxAge));
        return customerDto;
    }

    @Override
    public Page<CustomerDto> searchCustomer(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if(name != null && !name.isEmpty()) {
            criteria.add(Criteria.where("firstname").regex(name, "i"));
        }

        if(minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }

        if(city != null && !city.isEmpty()) {
            criteria.add(Criteria.where("address.city").is(city));
        }

        if(criteria.isEmpty()) {
            return null;
        }

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        Page<Customer> customers = PageableExecutionUtils.getPage(
                template.find(query, Customer.class),
                pageable,
                () -> template.count(query.skip(0).limit(0), Customer.class)
        );

        Page<CustomerDto> customerDto = new PageImpl(mapStudentToStudentDto(customers.getContent()));

        return customerDto;
    }

    @Override
    public List<Document> getOldestCustomerByCity() {
        UnwindOperation unwindOperation = Aggregation.unwind("address");
        SortOperation sortOperation     = Aggregation.sort(Sort.Direction.DESC, "age");
        GroupOperation groupOperation   = Aggregation.group("address.city").first(Aggregation.ROOT).as("oldestCustomer");
        Aggregation aggregation         = Aggregation.newAggregation(unwindOperation, sortOperation, groupOperation);
        List<Document>  documents       = template.aggregate(aggregation, Customer.class, Document.class).getMappedResults();
        return documents;
    }

    @Override
    public CustomerDto authenticate(CustomerLogin login) {
        String email = login.getUsername();
        Customer customer                  = findCustomerByEmail(email).orElse(null);
        ValidationResult customerValidator = validateCustomer(customer, login.getPassword());
        if(customerValidator               == ValidationResult.VALID) {
            assert customer != null;
            return CustomerDto.mapStudentToStudentDto(customer);
        }
        throw new IllegalStateException(customerValidator.name());
    }

    private Optional<Customer> findCustomerByEmail(String email) {
        return repository.findStudentByEmail(email);
    }

    private boolean customerExists(String email) {
        return findCustomerByEmail(email).isPresent();
    }

    private ValidationResult validateCustomer(Customer customer, String password) {
        return CustomerValidator.customerExists()
                .and(CustomerValidator.isCustomerPasswordValid(password))
                .and(CustomerValidator.isCustomerActive())
                .and(CustomerValidator.isCustomerEmailValid())
                .apply(customer);
    }

    private List<CustomerDto> mapStudentToStudentDto(List<Customer> customers) {
        return customers
                .stream()
                .map(CustomerDto::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }
}
