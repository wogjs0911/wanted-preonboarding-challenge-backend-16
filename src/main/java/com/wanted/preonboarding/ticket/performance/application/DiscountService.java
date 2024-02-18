package com.wanted.preonboarding.ticket.performance.application;

import com.wanted.preonboarding.ticket.performance.application.policy.DiscountFactory;
import com.wanted.preonboarding.ticket.performance.application.policy.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {

//    public PerformanceDiscountPolicy getDiscount(){
//        long productAmt = 10000L;
//        String discountCode = "NONE";
//        Discountable discountPolicy = getDiscounter(discountCode);
//        long discountAmt = discountPolicy.getDiscountAmt(productAmt);
//        return PerformanceDiscountPolicy;
//    }

    public long discount(long productAmt, String discountCode){
        DiscountFactory discountPolicy = getDiscounter(discountCode);
        return discountPolicy.getDiscountAmt(productAmt);
    }

    private DiscountFactory getDiscounter(String discountCode){
        if(discountCode == null){
            return DiscountFactory.NONE;
        }
        try {
            return DiscountPolicy.valueOf(discountCode);
        } catch (IllegalArgumentException iae) {
            log.warn("Not Found discountCode : {}", discountCode);
            return DiscountFactory.NONE;
        }
    }
}
