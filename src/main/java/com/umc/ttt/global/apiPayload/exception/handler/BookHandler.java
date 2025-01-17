package com.umc.ttt.global.apiPayload.exception.handler;

import com.umc.ttt.global.apiPayload.code.BaseErrorCode;
import com.umc.ttt.global.apiPayload.exception.GeneralException;

public class BookHandler extends GeneralException {
    public BookHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
