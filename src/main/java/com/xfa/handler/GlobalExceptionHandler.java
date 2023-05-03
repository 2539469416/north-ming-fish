package com.xfa.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liwen
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(BusinessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * 处理系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("error");
        return mv;
    }
}
