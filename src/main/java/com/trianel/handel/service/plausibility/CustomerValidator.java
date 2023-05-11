package com.trianel.handel.service.plausibility;

import com.trianel.handel.model.Customer;

import java.util.function.Function;

import static com.trianel.handel.service.plausibility.CustomerValidationResult.*;


public interface CustomerValidator extends Function<Customer, CustomerValidationResult> {

    static CustomerValidator findCustomerByID(String customerId) {
        return customer -> customer != null && customerId.equals(customer.getCustomerId()) ? VALID : CUSTOMER_NOT_FOUND_BY_ID;
    }

    static CustomerValidator customerExists() {
        return customer -> customer != null ? VALID : CUSTOMER_NOT_EXISTS;
    }

    static CustomerValidator isCustomerEmailValid() {
        return customer -> customer.getEmail().contains("@") ? VALID : EMAIL_NOT_VALID;
    }

    static CustomerValidator isCustomerActive() {
        return customer -> customer.getActive().booleanValue() ? VALID : CUSTOMER_NOT_ACTIVE;
    }

    static CustomerValidator isCustomerPasswordValid(String password) {
        return customer -> customer.getPassword()!=null && customer.getPassword().equals(password)  ? VALID : PASSWORD_NOT_VALID;
    }

    static CustomerValidator customerEmailAlreadyInUser(String email) {
        return customer -> customer != null && customer.getEmail().equalsIgnoreCase(email) ?
                VALID : CUSTOMER_EMAIL_ALREADY_IN_USE;
    }

    default CustomerValidator and(CustomerValidator other) {
        return employee -> {
            CustomerValidationResult result = this.apply(employee);
            return result.equals(CustomerValidationResult.VALID) ? other.apply(employee) : result;
        };
    }
}
