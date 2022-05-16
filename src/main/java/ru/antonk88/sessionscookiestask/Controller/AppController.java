package ru.antonk88.sessionscookiestask.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.antonk88.sessionscookiestask.Model.User;

@RestController
public class AppController {

    final String apiUrl = "http://94.198.50.185:7081/api/users";

    public ResponseEntity<User[]> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(apiUrl, User[].class);
    }

    public ResponseEntity<String> addUser(User user, String cookie) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return template.exchange(apiUrl, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> editUser(User user, String cookie) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return template.exchange(apiUrl, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<String> delUser(User user, String cookie) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(null, headers);
        return template.exchange(apiUrl + "/" + user.getId(), HttpMethod.DELETE, entity, String.class);
    }


}
