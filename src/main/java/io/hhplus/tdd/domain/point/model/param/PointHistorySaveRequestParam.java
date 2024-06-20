package io.hhplus.tdd.domain.point.model.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointHistorySaveRequestParam {

        private long userId;
        private long amount;
        private String type;
}
