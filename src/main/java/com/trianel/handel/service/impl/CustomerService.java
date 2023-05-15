package com.trianel.handel.service.impl;

import com.trianel.handel.model.Customer;
import com.trianel.handel.model.dto.customer.CustomerDto;
import com.trianel.handel.model.dto.customer.LoginDto;
import com.trianel.handel.repository.CustomerRepository;
import com.trianel.handel.service.ITrianelService;
import com.trianel.handel.service.exception.service.CustomerServiceException;
import com.trianel.handel.service.plausibility.customer.CustomerValidator;
import com.trianel.handel.service.plausibility.customer.CustomerValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trianel.handel.service.plausibility.customer.CustomerValidation.VALID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ITrianelService<CustomerDto> {

    private final CustomerRepository repository;
    private final MongoTemplate template;

    @Override
    public List<CustomerDto> getAllEntities() {
        return mapCustomerToCustomerDto(repository.findAll());
    }

    @Override
    public CustomerDto addEntity(CustomerDto requestDTO) {
        Customer customer               = requestDTO.mapCustomerDtoToCustomer(requestDTO);
        CustomerValidation apply  = CustomerValidator
                .isCustomerEmailValid()
                .apply(customer);
        log.info("ValidationResult: {}", apply);
        if(apply == VALID) {
            String email                        = requestDTO.getEmail();
            Optional<Customer> customerByEmail  = findCustomerByEmail(email);
            if(customerByEmail.isPresent()) {
                apply = CustomerValidator.customerEmailAlreadyInUser(email).apply(customerByEmail.get());
                if(apply != VALID) {
                    throw new CustomerServiceException(apply.getDescription());
                }
            }
            assert customer != null;
            Customer insert = repository.insert(customer);
            return CustomerDto.mapCustomerToCustomerDto(insert);
        }
        throw new CustomerServiceException(apply.getDescription());
    }

    @Override
    public CustomerDto updateEntity(Object object, CustomerDto requestDTO) {
        Customer customer      = requestDTO.mapCustomerDtoToCustomer(requestDTO);
        CustomerValidation apply = CustomerValidator
                .findCustomerByID(object.toString())
                .and(CustomerValidator.isCustomerEmailValid())
                .apply(customer);
        log.info("ValidationResult: {}", apply);
        if(apply == VALID) {
            assert customer != null;
            Customer insert = repository.save(customer);
            return CustomerDto.mapCustomerToCustomerDto(insert);
        }
        throw new CustomerServiceException(apply.getDescription());
    }

    @Override
    public Boolean deleteEntity(Object o) {
        if(customerExistsByID(o)) {
            repository.deleteById(o.toString());
            log.info("Customer deleted successfully");
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public CustomerDto authenticate(LoginDto login) {
        String email = login.getUsername();
        Customer customer                       = findCustomerByEmail(email).orElse(null);
        CustomerValidation customerValidator    = validateCustomer(customer, login.getPassword());
        if(customerValidator == VALID) {
            assert customer != null;
            return CustomerDto.mapCustomerToCustomerDto(customer);
        }
        throw new CustomerServiceException(customerValidator.name());
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

        Page<CustomerDto> customerDto = new PageImpl(mapCustomerToCustomerDto(customers.getContent()));

        return customerDto;
    }

    private Optional<Customer> findCustomerByEmail(String email) {
        return repository.findCustomerByEmail(email);
    }

    private boolean customerExistsByEmail(String email) {
        return findCustomerByEmail(email).isPresent();
    }

    private CustomerValidation validateCustomer(Customer customer, String password) {
        return CustomerValidator.customerExists()
                .and(CustomerValidator.isCustomerPasswordValid(password))
                .and(CustomerValidator.isCustomerActive())
                .and(CustomerValidator.isCustomerEmailValid())
                .apply(customer);
    }

    private List<CustomerDto> mapCustomerToCustomerDto(List<Customer> customers) {
        return customers
                .stream()
                .map(CustomerDto::mapCustomerToCustomerDto)
                .collect(Collectors.toList());
    }

    private boolean customerExistsByID(Object o) {
        String customerId = o.toString();
        Optional<Customer> findByCustomerId = repository.findCustomerByCustomerId(customerId);
        return findByCustomerId.isPresent();
    }
}
