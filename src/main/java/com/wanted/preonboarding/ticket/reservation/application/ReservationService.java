package com.wanted.preonboarding.ticket.reservation.application;

import com.wanted.preonboarding.ticket.performance.application.DiscountService;
import com.wanted.preonboarding.ticket.performance.domain.entity.Performance;
import com.wanted.preonboarding.ticket.performance.application.policy.DiscountPolicy;
import com.wanted.preonboarding.ticket.performance.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.reservation.domain.dto.ReserveInfo;
import com.wanted.preonboarding.ticket.reservation.domain.entity.Reservation;
import com.wanted.preonboarding.ticket.reservation.infrastructure.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final DiscountService discountService;

    public boolean reserve(ReserveInfo reserveInfo, String discountType) {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
                .orElseThrow(EntityNotFoundException::new);
        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            // 1. 결제 + 할인
            int price = info.getPrice();
            long discountPrice = discountService.discount(price, discountType);
            reserveInfo.setAmount(reserveInfo.getAmount() - discountPrice);
            // 2. 예매 진행
            reservationRepository.save(Reservation.of(reserveInfo));
            return true;

        } else {
            return false;
        }
    }

    public List<Reservation> getAllReservationList() {
        return reservationRepository.findAll()
                .stream()
                .toList();
    }

    public Reservation getReservationDetail(String name, String phoneNumber) {
        return reservationRepository.findByNameAndPhoneNumber(name, phoneNumber);
    }

}
