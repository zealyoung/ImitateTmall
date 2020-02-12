/**
 * @Author: ZealYoung
 * @Time: 2020/1/20 12:15 下午
 * @Description:
 */
package com.zeal.tmall.exception;

import javax.servlet.http.HttpServletRequest;

import com.zeal.tmall.util.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        String failMsg = null;
        if(e instanceof MethodArgumentNotValidException) {
            failMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            return Result.fail(failMsg);
        }

        e.printStackTrace();

        Class constraintViolationException = Class.forName("org.hibernate.exception.ConstraintViolationException");
        if(e.getCause() != null && constraintViolationException == e.getCause().getClass()) {
            return "违反了约束，多半是外键约束";
        }
        return e.getMessage();
    }

}
