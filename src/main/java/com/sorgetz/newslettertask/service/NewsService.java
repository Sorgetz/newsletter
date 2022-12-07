package com.sorgetz.newslettertask.service;

import com.sorgetz.newslettertask.dto.req.NewsReqDTO;
import com.sorgetz.newslettertask.dto.res.NewsResDTO;
import com.sorgetz.newslettertask.model.News;
import com.sorgetz.newslettertask.repository.NewsRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    public NewsResDTO create(NewsReqDTO dto) {

        if(dto.getTitle() == null) throw new ServiceException("News tittle cannot be null");
        if(dto.getDescription() == null) throw new ServiceException("News description cannot be null");

        News news = new News();
        BeanUtils.copyProperties(dto, news);
        news.setSent(Boolean.FALSE);

        this.repository.save(news);

        return NewsResDTO.toRes(news);
    }

    public List<News> getAllNotSentNews(){
        return this.repository.findAllBySentIsFalse();
    }

    public void updateNewsSent(News news, Boolean sent){
        news.setSent(sent);
        this.repository.save(news);
    }

}
