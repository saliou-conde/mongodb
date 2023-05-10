package com.trianel.handel.service.plausibility;

import com.trianel.handel.model.Customer;

import java.util.function.Function;


public interface CustomerValidator extends Function<Customer, ValidationResult> {

    static CustomerValidator customerExists() {
        return customer -> customer != null ? ValidationResult.VALID : ValidationResult.CUSTOMER_NOT_FOUND;
    }

    static CustomerValidator isCustomerEmailValid() {
        return customer -> customer.getEmail().contains("@") ? ValidationResult.VALID : ValidationResult.EMAIL_NOT_VALID;
    }

    static CustomerValidator isCustomerActive() {
        return customer -> customer.getActive().booleanValue() ? ValidationResult.VALID : ValidationResult.CUSTOMER_NOT_ACTIVE;
    }

    static CustomerValidator isCustomerPasswordValid(String password) {
        return customer -> customer.getPassword().equals(password)  ? ValidationResult.VALID : ValidationResult.PASSWORD_NOT_VALID;
    }

    default CustomerValidator and(CustomerValidator other) {
        return employee -> {
            ValidationResult result = this.apply(employee);
            return result.equals(ValidationResult.VALID) ? other.apply(employee) : result;
        };
    }
}
