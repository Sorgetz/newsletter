package com.sorgetz.newslettertask.dto.res;

import com.sorgetz.newslettertask.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResDTO {

    private Long id;

    private String title;

    private String description;

    private String link;

    private Boolean sent;

    public static NewsResDTO toRes(News news){
        return NewsResDTO
                .builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .link(news.getLink())
                .sent(news.getSent())
                .build();
    }

}
