package com.wanted.preonboarding.ticket.reservation.presentation;

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


    @PostMapping("/{discountType}")
    public boolean reservation(
            @PathVariable("discountType") String discountType
    ) {
        System.out.println("reservation");

        return reservationService.reserve(ReserveInfo.builder()
            .performanceId(UUID.fromString("4438a3e6-b01c-11ee-9426-0242ac180002"))
            .reservationName("유진호")
            .reservationPhoneNumber("010-1234-1234")
            .reservationStatus("reserve")
            .amount(200000)
            .round(1)
            .line('A')
            .seat(1)
            .build()
        , discountType);
    }
}
