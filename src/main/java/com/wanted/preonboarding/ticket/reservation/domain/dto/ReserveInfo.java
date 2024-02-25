package com.wanted.preonboarding.ticket.reservation.domain.dto;

import com.wanted.preonboarding.common.exception.BaseResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ReserveInfo extends BaseResDto {
    // 공연 및 전시 정보 + 예약자 정보
    private UUID performanceId;
    private String reservationName;
    private String reservationPhoneNumber;
    private String reservationStatus; // 예약; 취소;
    private long amount;
    private int round;
    private char line;
    private int seat;
    private String dType;
}
