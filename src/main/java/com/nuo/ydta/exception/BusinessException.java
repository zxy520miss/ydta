package com.nuo.ydta.exception;

import com.nuo.ydta.contances.SystemError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends BaseBusinessException {

    private static final long serialVersionUID = -5963248300925271486L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    public BusinessException() {
        super(SystemError.SYSTEM_INTERNAL_ERROR);
    }

    public BusinessException(String message) {
        super(SystemError.SYSTEM_INTERNAL_ERROR, message);
        LOGGER.error(message);
    }

    public BusinessException(Throwable cause) {
        super(SystemError.SYSTEM_INTERNAL_ERROR, cause.getMessage());
        LOGGER.error("", cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(SystemError.SYSTEM_INTERNAL_ERROR, message + ":" + cause.getMessage());
        LOGGER.error("{}", message, cause);
    }

    public BusinessException(IError error) {
        super(error);
    }

    public BusinessException(IError error, String extMessage) {
        super(error, extMessage);
        LOGGER.error(extMessage);
    }

    public BusinessException(IError error, Throwable cause) {
        super(error, cause.getMessage());
        LOGGER.error("", cause);
    }
}

