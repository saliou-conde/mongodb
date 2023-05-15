package com.trianel.handel.service.plausibility.order;

public enum SpotOrderValidation {

    SPOT_ORDER_VALID("SpotOder Valid"),
    SPOT_ORDER_NOT_POSSIBLE("SpotOrder not possible, due timeout: 13:30"),
    SPOT_ORDER_NOT_FOUND_BY_ID("SpotOrder not found by ID");


    private String description;

    SpotOrderValidation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
