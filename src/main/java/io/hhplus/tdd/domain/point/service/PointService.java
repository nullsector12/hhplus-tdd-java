package io.hhplus.tdd.domain.point.service;

import io.hhplus.tdd.domain.point.model.dto.PointHistoryDto;
import io.hhplus.tdd.domain.point.model.param.PointHistorySaveRequestParam;
import io.hhplus.tdd.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    public List<PointHistoryDto> getPointHistories(long userId) {

        return pointRepository.findPointHistoryByUserId(userId).stream().map(PointHistoryDto::fromVoToDto).toList();
    }
    
    public void savePointHistory(PointHistorySaveRequestParam param) {
        pointRepository.saveUserPoint(param);
    }


}
