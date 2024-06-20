package io.hhplus.tdd.domain.user.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.domain.point.model.enums.TransactionType;
import io.hhplus.tdd.domain.user.model.entity.UserPoint;
import io.hhplus.tdd.domain.user.model.vo.UserPointVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserPointTable userPointTable;

    // 1. 특정 회원 조회
    @Override
    public UserPointVo findByUserId(long id) {
        UserPoint userPoint = Optional.of(userPointTable.selectById(id))
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        return new UserPointVo().toVo(userPoint);
    }

    // 2. 회원 등록/업데이트
    @Override
    public UserPointVo registerUser(long id, long amount) {
        return new UserPointVo().toVo(userPointTable.insertOrUpdate(id, amount));
    }

}
