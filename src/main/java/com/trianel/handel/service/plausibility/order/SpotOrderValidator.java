package com.trianel.handel.service.plausibility.order;

import com.trianel.handel.model.SpotOrder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

import static com.trianel.handel.service.plausibility.order.SpotOrderValidation.SPOT_ORDER_NOT_POSSIBLE;
import static com.trianel.handel.service.plausibility.order.SpotOrderValidation.SPOT_ORDER_VALID;
import static java.time.temporal.ChronoUnit.DAYS;

public interface SpotOrderValidator extends Function<SpotOrder, SpotOrderValidation> {

    int TIMEOUT = 1331;

    static SpotOrderValidator isSpotOrderTimeout() {
        return spotOrder -> {
            LocalDateTime localDateTime = LocalDateTime.now();
            LocalTime localTime = localDateTime.toLocalTime();
            int result = localTime.getHour()*100 + localTime.getMinute();
            long dayBetween = DAYS.between(localDateTime.toLocalDate(), spotOrder.getCreatedAt().toLocalDate());
            boolean isTimeout = localDateTime.toLocalDate().equals(spotOrder.getCreatedAt().toLocalDate());
            if(dayBetween > 1) {
                result = TIMEOUT - 1;
                isTimeout = true;
            }
            return isTimeout && result < TIMEOUT ? SPOT_ORDER_VALID : SPOT_ORDER_NOT_POSSIBLE;
        };
    }

    default SpotOrderValidator and(SpotOrderValidator other) {
        return spotOrder -> {
            SpotOrderValidation result = this.apply(spotOrder);
            return result.equals(SPOT_ORDER_VALID) ? other.apply(spotOrder) : result;
        };
    }
}
