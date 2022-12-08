package com.sorgetz.newslettertask.service;


import com.sorgetz.newslettertask.model.News;
import com.sorgetz.newslettertask.model.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class SchedulerService {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private MailService mailService;

    private static final String TIME_ZONE = "America/Sao_Paulo";

//    @Scheduled(cron = "0 0 8 * * *", zone = TIME_ZONE)
    @Scheduled(cron = "0 5 0 * * *", zone = TIME_ZONE)
    public void sendNewsEmail(){

        log.info("Starting to send emails to users at " + LocalDateTime.now());

        StringBuilder newsText = new StringBuilder();
        newsText.append("<p> Segue as notícias de hoje. </p>");

        List<News> newsList = this.newsService.getAllNotSentNews();

        if(!newsList.isEmpty()) {
            newsList.forEach(news -> {

                String title = news.getLink() != null
                        ? String.format("<a href=\"%s\"> %s </a>", news.getLink(), news.getTitle())
                        : news.getTitle();

                newsText.append(String.format(
                        "<p> <b> %s </b> <br /> %s </p>", title, news.getDescription()));

                this.newsService.updateNewsSent(news, Boolean.TRUE);
            });

            this.userService.getAllUsers().forEach(user -> {

                String subject = "Notícias do dia!";
                String birthday = this.userService.isUserBirthday(user) ? "Feliz aniversário!!!" : "";
                String body = String.format(
                                "<p>Bom dia %s! %s</p> %s" +
                                "<p> Até a próxima.</p>", user.getName(), birthday, newsText);

                this.mailService.sendMail(user.getEmail(), subject, body);

                log.info("Sending email to " + user.getEmail());

            });

        }else log.info("No news today");

    }


}
