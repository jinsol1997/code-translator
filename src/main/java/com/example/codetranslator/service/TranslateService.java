package com.example.codetranslator.service;

import com.example.codetranslator.domain.UserRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslateService {

    @Value("${openai.api.key}")
    private String apiKey;

    public ResponseEntity<String> translate(UserRequest userRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String url = "https://api.openai.com/v1/chat/completions";
        String json = userRequest.toJson();

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(json);
        return restTemplate.postForEntity(url, request, String.class);
    }

}
