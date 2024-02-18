package com.wanted.preonboarding.ticket.performance.application.policy;

public enum DiscountPolicy implements DiscountFactory {
    AMT(1000L, 0){
        @Override
        public long getDiscountAmt(long originAmt) {
            if(originAmt < this.discountAmt)
                return originAmt;
            return this.discountAmt;
        }
    },
    RATE(0, 15L){
        @Override
        public long getDiscountAmt(long originAmt) {
            return originAmt * this.discountRate / 100;
        }
    };

    public final long discountAmt;
    public final long discountRate;

    DiscountPolicy(long discountAmt, long discountRate) {
        this.discountAmt = discountAmt;
        this.discountRate = discountRate;
    }
}
