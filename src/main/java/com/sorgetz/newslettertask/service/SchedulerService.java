package com.sorgetz.newslettertask.service;


import com.sorgetz.newslettertask.model.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void sendNewsEmail(){

        log.info("Starting to send emails to users at " + LocalDate.now());

        StringBuilder newsText = new StringBuilder();
        newsText.append("<p> Segue as notícias de hoje. </p>");

        this.newsService.getAllNotSentNews().forEach(news -> {

            String title = news.getLink() != null
                    ? String.format("<a href=\"%s\"> %s </a>", news.getLink(), news.getTitle())
                    : news.getTitle();

            newsText.append(String.format(
                            "<p> <b> %s </b> </p>" +
                            "%s" , title, news.getDescription()));

            this.newsService.updateNewsSent(news, Boolean.TRUE);

        });

        List<Users> users = this.userService.getAllUsers();

        users.forEach(user -> {

            String subject = "Notícias do dia!";
            String birthday = this.userService.isUserBirthday(user) ? "Feliz aniversário" : "";
            String body = String.format(
                    "<p>Bom dia %s! %s</p> %s", user.getName(), birthday, newsText);

            this.mailService.sendMail(user.getEmail(), subject, body);

            log.info("Sending email to " + user.getEmail());

        });

    }


}
