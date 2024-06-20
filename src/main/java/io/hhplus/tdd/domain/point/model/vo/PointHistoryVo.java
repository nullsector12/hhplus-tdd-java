package io.hhplus.tdd.domain.point.model.vo;

import io.hhplus.tdd.domain.point.model.entity.PointHistory;
import io.hhplus.tdd.domain.point.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryVo {

    private long id;
    private long userId;
    private long point;
    private TransactionType type;
    private long updateMillis;

    public PointHistoryVo(PointHistory pointHistory) {
        this.id = pointHistory.id();
        this.userId = pointHistory.userId();
        this.point = pointHistory.amount();
        this.type = pointHistory.type();
    }
}
