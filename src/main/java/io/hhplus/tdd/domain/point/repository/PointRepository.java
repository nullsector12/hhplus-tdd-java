package io.hhplus.tdd.domain.point.repository;

import io.hhplus.tdd.domain.point.model.entity.PointHistory;
import io.hhplus.tdd.domain.point.model.param.PointHistorySaveRequestParam;
import io.hhplus.tdd.domain.point.model.vo.PointHistoryVo;

import java.util.List;

public interface PointRepository {

    // 1. 특정 회원 포인트 내역 조회
    List<PointHistoryVo> findPointHistoryByUserId(long id);

    // 2. 회원 포인트 적립/사용 내역 저장
    void saveUserPoint(PointHistorySaveRequestParam param);

}
