package com.csipon.planningpocker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/session")
public class SessionController {


    @PostMapping("/create")
    public ResponseEntity<String> session(HttpServletRequest request){
        log.debug(request.getSession().getId());
        return ResponseEntity.ok("OK " + request.getSession().getId());
    }
}
