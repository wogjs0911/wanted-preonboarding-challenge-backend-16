package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.TicketSeller;
import com.wanted.preonboarding.ticket.domain.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.domain.entity.Reservation;
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
public class QueryController {
    private final TicketSeller ticketSeller;

    @GetMapping("/all/performance")
    public ResponseEntity<ResponseHandler<List<PerformanceInfo>>> getAllPerformanceInfoList() {
        System.out.println("getAllPerformanceInfoList");
        return ResponseEntity
            .ok()
            .body(ResponseHandler.<List<PerformanceInfo>>builder()
                .message("Success")
                .statusCode(HttpStatus.OK)
                .data(ticketSeller.getAllPerformanceInfoList())
                .build()
            );
    }

    @GetMapping("/performance")
    public ResponseEntity<ResponseHandler<PerformanceInfo>> getPerformanceInfoDetail(
            @RequestParam(name = "name", required = false) String name
    ) {
        System.out.println("getPerformanceInfoDetail");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<PerformanceInfo>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(ticketSeller.getPerformanceInfoDetail(name))
                        .build()
                );
    }


    @GetMapping("/all/reservation")
    public ResponseEntity<ResponseHandler<List<Reservation>>> getAllReservationList() {
        System.out.println("getAllReservationList");
        return ResponseEntity
                .ok()
                .body(ResponseHandler.<List<Reservation>>builder()
                        .message("Success")
                        .statusCode(HttpStatus.OK)
                        .data(ticketSeller.getAllReservationList())
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
                        .data(ticketSeller.getReservationDetail(name, phoneNumber))
                        .build()
                );
    }

}
