package com.umc.ttt.global.apiPayload.exception.handler;

import com.umc.ttt.global.apiPayload.code.BaseErrorCode;
import com.umc.ttt.global.apiPayload.exception.GeneralException;

public class PlaceHandler extends GeneralException {
    public PlaceHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
