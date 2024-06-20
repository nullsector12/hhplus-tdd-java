package io.hhplus.tdd.domain.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.domain.point.model.entity.PointHistory;
import io.hhplus.tdd.domain.point.model.enums.TransactionType;
import io.hhplus.tdd.domain.point.model.param.PointHistorySaveRequestParam;
import io.hhplus.tdd.domain.point.model.vo.PointHistoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

    private final PointHistoryTable pointHistoryTable;

    // 1. 특정 회원 포인트 내역 조회
    @Override
    public List<PointHistoryVo> findPointHistoryByUserId(long userId) {

        return pointHistoryTable.selectAllByUserId(userId).stream().map(PointHistoryVo::new).toList();
    }

    // 2. 회원 포인트 적립/사용 내역 저장
    @Override
    public void saveUserPoint(PointHistorySaveRequestParam param) {
        pointHistoryTable.insert(param.getUserId(), param.getAmount(), TransactionType.valueOf(param.getType()), System.currentTimeMillis());
    }

}
