package io.hhplus.tdd.domain.user.model.vo;

import io.hhplus.tdd.domain.user.model.entity.UserPoint;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPointVo {

    private Long id;
    private Long point;


    public UserPointVo toVo (UserPoint userPoint) {
        this.id = userPoint.id();
        this.point = userPoint.point();

        return this;
    }
}
