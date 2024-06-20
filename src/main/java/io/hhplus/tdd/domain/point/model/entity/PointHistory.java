package io.hhplus.tdd.domain.point.model.entity;

import io.hhplus.tdd.domain.point.model.enums.TransactionType;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {
}
