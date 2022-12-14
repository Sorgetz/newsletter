package com.sorgetz.newslettertask.repository;

import com.sorgetz.newslettertask.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>{

    List<News> findAllBySentIsFalse();

}
