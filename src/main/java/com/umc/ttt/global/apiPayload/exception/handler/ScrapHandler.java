package com.umc.ttt.global.apiPayload.exception.handler;

import com.umc.ttt.global.apiPayload.code.BaseErrorCode;
import com.umc.ttt.global.apiPayload.exception.GeneralException;

public class ScrapHandler extends GeneralException {
    public ScrapHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
