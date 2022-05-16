package ru.antonk88.sessionscookiestask.Service;

import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.antonk88.sessionscookiestask.Controller.AppController;
import ru.antonk88.sessionscookiestask.Model.User;

import java.util.Arrays;
import java.util.List;

@Component
public class PostConstruct {
    AppController appController;


    @Autowired
    public PostConstruct(AppController appController) {
        this.appController = appController;
    }

    @javax.annotation.PostConstruct
    public void init() {

        ResponseEntity<User[]> response = appController.getUsers();
        List<User> userList = Arrays.asList(response.getBody());
        String sessionCookie = response.getHeaders().get("Set-Cookie").get(0);

        System.out.println("Cookie= " + sessionCookie);

        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 20);

        String response2 = appController.addUser(newUser, sessionCookie).getBody();
        System.out.println(response2);

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        String response3 = appController.editUser(newUser, sessionCookie).getBody();
        System.out.println(response3);

        String response4 = appController.delUser(newUser, sessionCookie).getBody();
        System.out.println(response4);

        System.out.println("Ответ: " + response2 + response3 + response4);
    }
}
