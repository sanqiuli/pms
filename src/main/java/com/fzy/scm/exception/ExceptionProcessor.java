package com.fzy.scm.exception;

import com.fzy.scm.entity.rest.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;


/**
 * @program: ExceptionHandler
 * @description: 权限异常处理
 * @author: fzy
 * @date: 2019/03/17 08:54:32
 **/
@Slf4j
@ControllerAdvice
public class ExceptionProcessor {
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        // 打印堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));
        return Result.failure(HttpStatus.BAD_REQUEST.value(),e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @returns
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        return Result.failure(HttpStatus.BAD_REQUEST.value(), str[1] + ":" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public Result badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ExceptionUtils.getStackTrace(e));
        return Result.failure(e.getCode(),e.getMessage());
    }
}
