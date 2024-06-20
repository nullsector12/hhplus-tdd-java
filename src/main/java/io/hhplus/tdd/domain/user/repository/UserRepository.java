package io.hhplus.tdd.domain.user.repository;

import io.hhplus.tdd.domain.user.model.vo.UserPointVo;

import java.util.Optional;

public interface UserRepository {

    // 1. 특정 회원 조회
    UserPointVo findByUserId(long id);

    // 2. 회원 등록
    UserPointVo registerUser(long id, long amount);

}
