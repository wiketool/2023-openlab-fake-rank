package com.opanlab.fakerank;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exception(){
        return "欢迎加入兴趣开放实验室";
    }

}
