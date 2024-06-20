package io.hhplus.tdd.domain.user.service;

import io.hhplus.tdd.domain.user.model.dto.UserPointDto;
import io.hhplus.tdd.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserPointDto findById(long id) {
        return UserPointDto.fromEntity(userRepository.findByUserId(id));
    }

    public UserPointDto registerUser(long id , long amount) {
        return UserPointDto.fromEntity(userRepository.registerUser(id, amount));
    }

}
