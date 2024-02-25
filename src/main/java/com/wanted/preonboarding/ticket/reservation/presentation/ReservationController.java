package com.wanted.preonboarding.ticket.reservation.presentation;

import com.wanted.preonboarding.common.exception.ServiceException;
import com.wanted.preonboarding.ticket.reservation.application.ReservationService;
import com.wanted.preonboarding.ticket.reservation.domain.dto.ReserveInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public boolean reservation() throws ServiceException {
        System.out.println("reservation");

        return reservationService.reserve(ReserveInfo.builder()
            .performanceId(UUID.fromString("7601e052-c440-11ee-9f73-0242ac150002"))
            .reservationName("유진호")
            .reservationPhoneNumber("010-1234-1234")
            .reservationStatus("reserve")
            .amount(200000)
            .round(1)
            .line('A')
            .seat(1)
            .dType("AMT")
            .build());
    }
}
