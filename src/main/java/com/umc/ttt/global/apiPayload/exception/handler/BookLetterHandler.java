package com.umc.ttt.global.apiPayload.exception.handler;

import com.umc.ttt.global.apiPayload.code.BaseErrorCode;
import com.umc.ttt.global.apiPayload.exception.GeneralException;

public class BookLetterHandler extends GeneralException {
    public BookLetterHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
