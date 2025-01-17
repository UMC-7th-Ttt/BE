package com.umc.ttt.global.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.umc.ttt.global.apiPayload.code.BaseErrorCode;
import com.umc.ttt.global.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 나들이 북클럽
    INVALID_SERVICE_KEY(HttpStatus.UNAUTHORIZED, "PLACE401", "서비스 키가 유효하지 않습니다."),
    SERVICE_URL_UNREACHABLE(HttpStatus.UNAUTHORIZED, "PLACE401", "서비스 주소 호출에 실패했습니다."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "PLACE503", "서비스 점검중입니다.(내부 서비스 호출 장애)"),

    // 책 관련 에러
    BOOK_NOT_FOUND(HttpStatus.BAD_REQUEST, "BOOK401", "책을 찾을 수 없습니다."),

    // 북레터
    BOOKLETTER_NOT_FOUND(HttpStatus.BAD_REQUEST, "BOOKLETTER401", "북레터를 찾을 수 없습니다."),
    BOOKLETTER_BOOKLIST_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "BOOKLETTER402","북레터의 책은 최대 5권입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
