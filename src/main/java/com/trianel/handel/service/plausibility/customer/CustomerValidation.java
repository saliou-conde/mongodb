package com.trianel.handel.service.plausibility.customer;

public enum CustomerValidation {

    VALID("Customer Valid"),
    CUSTOMER_NOT_EXISTS("Customer not found"),
    CUSTOMER_NOT_FOUND_BY_ID("Customer not found by ID"),
    EMAIL_NOT_VALID("Customer Email not Valid"),
    CUSTOMER_NOT_ACTIVE("Customer not active"),
    PASSWORD_NOT_VALID("Customer Password not correct"),
    CUSTOMER_EMAIL_ALREADY_IN_USE("Custer Email already in use");

    private String description;

     CustomerValidation(String description) {
         this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
