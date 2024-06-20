package io.hhplus.tdd.point.unitTest;

import io.hhplus.tdd.domain.user.model.dto.UserPointDto;
import io.hhplus.tdd.domain.user.model.entity.UserPoint;
import io.hhplus.tdd.domain.user.model.vo.UserPointVo;
import io.hhplus.tdd.domain.user.repository.UserRepository;
import io.hhplus.tdd.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
     * 1. 특정 유저의 포인트 충전/이용 내역 조회 기능 TC
     *
     * 1.1. 요청 온 유저 이외의 다른 유저를 조회하면 안된다.
     * 1.2. 특정 유저의 포인트 적립 시 회원의 잔여 포인트가 증가해야한다.
     * 1.3. 특정 유저의 포인트 사용 시 회원의 잔여 포인트가 감소해야한다.
     * */


    /*
     * 요청이 들어온 회원을 정확히 조회가능 해야한다.
     * 회원이 현재 가지고 있는 포인트의 값이 일치해야한다.
     * */
    @Test
    @Description("특정 유저 조회 기능 TC")
    void findUserTest() {
        // given
        long id = 1;
        long currentPoint = 1000;
        UserPointVo userPointVo = new UserPointVo(id, currentPoint);
        when(userRepository.findByUserId(any(Long.class))).thenReturn(userPointVo);

        // when
        UserPointDto user = userService.findById(id);

        // then
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getPoint()).isEqualTo(currentPoint);
    }
}
