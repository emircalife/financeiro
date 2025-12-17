package com.calife.financeiro.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class HtmxControllerAdvice {

    @ModelAttribute("isHtmxRequest")
    public boolean isHtmxRequest(HttpServletRequest request) {
        return "true".equals(request.getHeader("HX-Request"));
    }
}
