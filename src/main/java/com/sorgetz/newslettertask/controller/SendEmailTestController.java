package com.sorgetz.newslettertask.controller;

import com.sorgetz.newslettertask.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailTestController {

    @Autowired
    private SchedulerService service;

    @PostMapping("/mail/")
    public void sendMail(){
        this.service.sendNewsEmail();
    }

}
