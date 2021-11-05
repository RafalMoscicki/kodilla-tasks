package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/task");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "message generated automatically");
        context.setVariable("goodbye", "Thanks for using our CRUD APP");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String sendOneMsgPerDay(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview", "message generated automatically");
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("tasks_url", "http://localhost:8888/task");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", "Thanks for using our CRUD APP");
        return templateEngine.process("mail/tasks-number-in-crud-app", context);
    }
}
