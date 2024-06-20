package io.hhplus.tdd.domain.user.model.dto;

import io.hhplus.tdd.domain.user.model.vo.UserPointVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPointDto {

    private Long id;
    private Long point;


    public static UserPointDto fromEntity (UserPointVo vo) {
        return UserPointDto.builder()
                .id(vo.getId())
                .point(vo.getPoint())
                .build();
    }
}
