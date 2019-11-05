package com.nuo.ydta.handler;

import com.nuo.ydta.contances.ProjectError;
import com.nuo.ydta.exception.BusinessException;
import com.nuo.ydta.exception.IError;
import com.nuo.ydta.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class YdtaExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
        public Response handle(Exception e){
            if(e instanceof BusinessException){
                log.error("自定义异常",e);
                IError error = ((BusinessException) e).getError();
                return Response.create(error);
            }else{
                log.error("系统异常",e);
                return Response.create(e);
            }
        }

}
