package com.wanted.preonboarding.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResDto {
    private int resultCode = ResultCode.SUCCESS.getResultCode();
    private String resultMessage = ResultCode.SUCCESS.getResultMessage();
}
