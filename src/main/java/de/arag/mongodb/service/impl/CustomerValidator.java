package de.arag.mongodb.service.impl;

import de.arag.mongodb.model.Customer;

import java.util.function.Function;

import static de.arag.mongodb.service.impl.ValidationResult.*;


public interface CustomerValidator extends Function<Customer, ValidationResult> {

    static CustomerValidator customerExists() {
        return customer -> customer != null ? VALID : CUSTOMER_NOT_FOUND;
    }

    static CustomerValidator isCustomerEmailValid() {
        return customer -> customer.getEmail().contains("@") ? VALID : EMAIL_NOT_VALID;
    }

    static CustomerValidator isCustomerActive() {
        return customer -> customer.getActive().booleanValue() ? VALID : CUSTOMER_NOT_ACTIVE;
    }

    static CustomerValidator isCustomerPasswordValid(String password) {
        return customer -> customer.getPassword().equals(password)  ? VALID : PASSWORD_NOT_VALID;
    }

    default CustomerValidator and(CustomerValidator other) {
        return employee -> {
            ValidationResult result = this.apply(employee);
            return result.equals(VALID) ? other.apply(employee) : result;
        };
    }
}
