package com.wanted.preonboarding.ticket.performance.application.policy;
public interface DiscountFactory {

    DiscountFactory NONE = new DiscountFactory() {
        @Override
        public long getDiscountAmt(long originAmt) {
            return 0;
        }
    };
    long getDiscountAmt(long originAmt);
}
