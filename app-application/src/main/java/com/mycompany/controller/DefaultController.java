package com.mycompany.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author john
 */

@RestController
public class DefaultController {
    @RequestMapping
    public void error(HttpServletRequest request) throws NoHandlerFoundException {
        throw new NoHandlerFoundException(request.getMethod(),request.getRequestURI(),new HttpHeaders());
    }
}
