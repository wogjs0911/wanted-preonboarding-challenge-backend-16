package com.wanted.preonboarding.ticket.reservation.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.reservation.application.ReservationService;
import com.wanted.preonboarding.ticket.reservation.domain.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("query")
@RequiredArgsConstructor
public class QueryReservationController {

    private final ReservationService reservationService;

    @GetMapping("/all/reservation")
    public ResponseEntity<ResponseHandler<List<Reservation>>> getAllReservationList() {
        System.out.println("getAllReservationList");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<List<Reservation>>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(reservationService.getAllReservationList())
                        .build()
                );
    }

    @GetMapping("reservation")
    public ResponseEntity<ResponseHandler<Reservation>> getReservationDetail(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber
    ) {
        System.out.println("getReservationDetail");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<Reservation>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(reservationService.getReservationDetail(name, phoneNumber))
                        .build()
                );
    }
}
