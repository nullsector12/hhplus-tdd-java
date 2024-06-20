package io.hhplus.tdd.domain.point.model.dto;

import io.hhplus.tdd.domain.point.model.vo.PointHistoryVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PointHistoryDto {

    private long id;
    private long userId;
    private long amount;
    private String type;
    private long updateMillis;

    public static PointHistoryDto fromVoToDto(PointHistoryVo vo) {
        return PointHistoryDto.builder()
                .id(vo.getId())
                .userId(vo.getUserId())
                .amount(vo.getPoint())
                .type(vo.getType().name())
                .updateMillis(vo.getUpdateMillis())
                .build();
    }
}
