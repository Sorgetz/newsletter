package com.sorgetz.newslettertask.controller;

import com.sorgetz.newslettertask.dto.req.NewsReqDTO;
import com.sorgetz.newslettertask.dto.res.NewsResDTO;
import com.sorgetz.newslettertask.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    private NewsService service;

    @PostMapping("/news/")
    public NewsResDTO create(@RequestBody NewsReqDTO dto){
        return this.service.create(dto);
    }

}
