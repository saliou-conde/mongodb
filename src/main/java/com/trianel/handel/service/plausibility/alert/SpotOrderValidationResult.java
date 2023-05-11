package com.trianel.handel.service.plausibility.alert;

public enum SpotOrderValidationResult {

    SPOT_ORDER_VALID("SpotOder Valid"),
    SPOT_ORDER_NOT_POSSIBLE("SpotOrder not possible, due timeout: 13:30");


    private String description;

    SpotOrderValidationResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
