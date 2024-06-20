package io.hhplus.tdd.point.unitTest;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.domain.point.model.dto.PointHistoryDto;
import io.hhplus.tdd.domain.point.model.entity.PointHistory;
import io.hhplus.tdd.domain.point.model.vo.PointHistoryVo;
import io.hhplus.tdd.domain.point.repository.PointRepository;
import io.hhplus.tdd.domain.point.service.PointService;
import io.hhplus.tdd.domain.user.model.dto.UserPointDto;
import io.hhplus.tdd.domain.user.model.entity.UserPoint;
import io.hhplus.tdd.domain.point.model.enums.TransactionType;
import io.hhplus.tdd.domain.user.model.vo.UserPointVo;
import io.hhplus.tdd.domain.user.repository.UserRepository;
import io.hhplus.tdd.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PointUnitTest {


    /*
    * 1. 특정 유저의 포인트 충전/이용 내역 조회 기능 TC
    *
    * 1.1. 요청 온 유저 이외의 다른 유저를 조회하면 안된다. (회원 유닛테스트로 이동)
    * 1.2. 요청 온 유저 이외의 다른 유저의 포인트를 조회하면 안된다.
    * 1.3. 전체조회) 충전 OR 사용 내역만 조회하면 안된다.
    * 1.4. 내역이 기록된 시간 순으로 조회되어야 한다.
    * 1.5. 선택조회) 선택한 내역 이외의 내역을 조회하면 안된다. (아직 케이스가 존재하지않음)
    * 1.6. ?
    * */

    @Mock
    private UserRepository userRepository;

    @Mock
    private PointRepository pointRepository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private PointService pointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
     * 요청이 들어온 회원의 포인트 적립/사용내역을 정확히 조회가능 해야한다.
     * 회원이 현재 가지고 있는 포인트와 내역의 포인트 총합 값이 일치해야한다.
     * */
    @Test
    @Description("특정 유저의 포인트 적립/사용내역 조회 기능 TC")
    void findUserPointHistoryTest() {
        // given
        long id = 1;
        long currentPoint = 300;
        UserPointVo userPointVo = new UserPointVo(id, currentPoint);
        when(userRepository.findByUserId(id)).thenReturn(userPointVo);

        long point = 100;
        TransactionType transactionType = TransactionType.CHARGE;
        List<PointHistoryVo> pointHistoryList = List.of(
            new PointHistoryVo(1,1, point, transactionType, System.currentTimeMillis()),
            new PointHistoryVo(2,1, 200, transactionType, System.currentTimeMillis()),
            new PointHistoryVo(3,2, 300, TransactionType.USE, System.currentTimeMillis())
        );
        when(pointRepository.findPointHistoryByUserId(id)).thenReturn(pointHistoryList);

        // when
        UserPointDto user = userService.findById(id);
        List<PointHistoryDto> pointHistory = pointService.getPointHistories(user.getId());
        long totalPoint = pointHistory.stream().filter(e -> e.getUserId() == id).mapToLong(p -> {
            if (p.getType().equals(TransactionType.CHARGE.name())) {
                return p.getAmount();
            } else {
                return -p.getAmount();
            }
        }).sum();

        // then
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPoint()).isEqualTo(currentPoint);

        PointHistoryDto pointHistoryDto = pointHistory.get(0);

        assertThat(pointHistoryDto.getUserId()).isEqualTo(id);
        assertThat(pointHistoryDto.getAmount()).isEqualTo(point);
        assertThat(pointHistoryDto.getType()).isEqualTo(transactionType.name());

        assertThat(totalPoint).isEqualTo(currentPoint);
    }

}
