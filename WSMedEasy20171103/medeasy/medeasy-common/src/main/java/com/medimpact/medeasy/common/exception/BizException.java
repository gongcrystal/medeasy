package com.medimpact.medeasy.common.exception;

/**
 * 业务异常
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BizException(String code, String message) {
        super(message);
    }

    public BizException(String code,String message, Throwable cause) {
        super(message, cause);
    }

}
