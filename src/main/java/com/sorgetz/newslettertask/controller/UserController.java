package com.sorgetz.newslettertask.controller;

import com.sorgetz.newslettertask.dto.req.UserReqDTO;
import com.sorgetz.newslettertask.dto.res.UserResDTO;
import com.sorgetz.newslettertask.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/users/")
    public UserResDTO create(@RequestBody @Valid UserReqDTO dto){
        return this.service.create(dto);
    }

}