package com.dadingcoding.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //4XX Client error response
    //400 Bad Request
    NOT_AVAILABLE_TOKEN("토큰이 유효하지 않습니다."),
    WRONG_TOKEN_TYPE("올바르지 않은 토큰입니다."),
    EXPIRED_TOKEN("토큰이 만료되었습니다. 다시 로그인하세요."),
    BAD_CREDENTIALS("회원정보가 없습니다."),
    WRONG_ID_PASSWORD("잘못된 이메일 또는 패스워드입니다."),

    //403 Forbidden
    NO_ATHORITY_TO_ACCESS("데이터에 접근 권한이 없습니다."),
    SCHEDULE_TO_WAIT("아직 열리지 않은 스케줄입니다."),
    SCHEDULE_TO_TERM("종료된 스케줄입니다."),

    //404 NOT FOUND
    NO_SUCH_ENTITY("존재하지 않는 데이터입니다."),
    NO_SUCH_MEMBER("존재하지 않는 유저입니다."),
    NO_SUCH_SCHEDULE("존재하지 않는 스케줄입니다."),

    //5XX Server error response
    INTERNAL_SERVER_ERROR("서버 내부 오류");

    private String message;
}
