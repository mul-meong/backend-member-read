package com.mulmeong.member.read.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static com.mulmeong.member.read.common.response.BaseResponseStatus.SUCCESS;

public record BaseResponse<T>(HttpStatusCode httpStatus, Boolean isSuccess, String message, int code, T result) {

    // 필요값 : Http 상태코드, 성공여부, 메시지, 에러코드, 결과값

    /**
     * 1. Return 객체가 필요한 경우 -> 성공.
     *
     * @param result 반환해야 하는 결과 객체
     */
    public BaseResponse(T result) {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), result);
    }

    /**
     * 2. Return 객체가 필요 없는 경우 -> 성공.
     */
    public BaseResponse() {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), null);
    }

    /**
     * 3. 요청에 실패한 경우.
     *
     * @param status 요청 상태
     */
    public BaseResponse(BaseResponseStatus status) {
        this(status.getHttpStatusCode(), false, status.getMessage(), status.getCode(), (T) status.getMessage());
    }

    /**
     * 4. 요청에 실패한 경우 + 에러 객체(메시지) 추가.
     *
     * @param status 요청 상태
     * @param e      에러 객체 => e.getMessage()로 에러 메시지 전달
     */
    public BaseResponse(BaseResponseStatus status, Exception e) {
        this(status.getHttpStatusCode(),
                false,
                e.getMessage(),
                status.getCode(),
                (T) status.getMessage());
    }

    /**
     * 5. 요청에 실패한 경우 + 에러 메시지 추가.
     * Validation 실패 시 result에 validation 오류 메시지를 넣어주기 위해서 사용
     *
     * @param status  요청 상태
     * @param errorMessage 에러 메시지
     */
    public BaseResponse(BaseResponseStatus status, String errorMessage) {
        this(
                status.getHttpStatusCode(),
                false,
                status.getMessage(),
                status.getCode(),
                (T) errorMessage // result에 오류 메시지를 넣음
        );
    }
}