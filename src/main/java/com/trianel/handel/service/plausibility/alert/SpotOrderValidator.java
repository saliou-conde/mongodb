package com.trianel.handel.service.plausibility.alert;

import com.trianel.handel.model.SpotOrder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

import static com.trianel.handel.service.plausibility.alert.SpotOrderValidationResult.SPOT_ORDER_NOT_POSSIBLE;
import static com.trianel.handel.service.plausibility.alert.SpotOrderValidationResult.SPOT_ORDER_VALID;
import static java.time.temporal.ChronoUnit.DAYS;

public interface SpotOrderValidator extends Function<SpotOrder, SpotOrderValidationResult> {

    int TIMEOUT = 1331;

    static SpotOrderValidator isSpotOrderTimeout() {

        return spotOrder -> {
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalTime localTime = localDateTime.toLocalTime();
            int result = localTime.getHour()*100 + localTime.getMinute();
            long dayBetween = DAYS.between(localDateTime.toLocalDate(), spotOrder.getTimestamp().toLocalDate());
            boolean isTimeout = localDateTime.toLocalDate().equals(spotOrder.getTimestamp().toLocalDate());
            if(dayBetween > 1) {
                result = TIMEOUT - 1;
                isTimeout = true;
            }
            return isTimeout && result < TIMEOUT ? SPOT_ORDER_VALID : SPOT_ORDER_NOT_POSSIBLE;
        };

    }
}
