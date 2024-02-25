package com.wanted.preonboarding.ticket.reservation.application;

import com.wanted.preonboarding.common.exception.ResultCode;
import com.wanted.preonboarding.common.exception.ServiceException;
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

    public boolean reserve(ReserveInfo reserveInfo) throws ServiceException {
        log.info("reserveInfo ID => {}", reserveInfo.getPerformanceId());
        Performance info = performanceRepository.findById(reserveInfo.getPerformanceId())
                .orElseThrow(EntityNotFoundException::new);
        String enableReserve = info.getIsReserve();
        if (enableReserve.equalsIgnoreCase("enable")) {
            /**
             * 1. 유효성 검사 : 예약 테이블의 PK 검사 후 에러 처리하기
             *
             * a. 해당 유저의 정보를 조회하는 API를 호출 했을 때, ReservationService의 reserve 함수를 호출한다.
             * b. JPA로 User Entity를 조회하는 동시에 orElseThrow를 통해 해당 유저 데이터가 존재하지 않을 때, ServiceException에 미리 정의 해놓은 ResultCode를 담아서 throw한다.
             * c. ControllerAdvice Annotation을 선언해놓은 GlobalExceptionHandler가 @ExceptionHandler(ServiceException.class)가 선언 되어 있는 Method를 호출한다.
             * ** 아래 로직에서는 반대 로직이여야 한다. Null이 아니면 에러 메세지를 발생할 수 있도록
             */
            Reservation reservation = reservationRepository.findByPerformanceId(reserveInfo.getPerformanceId())
                    .orElseThrow(()-> new ServiceException(ResultCode.DUPLICATE_INFO));

            // 2. 결제 + 할인
            int price = info.getPrice();
            String discountType = reserveInfo.getDType();
            long discountPrice = discountService.discount(price, discountType);
            reserveInfo.setAmount(reserveInfo.getAmount() - discountPrice);


            // 3. 예매 진행
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
