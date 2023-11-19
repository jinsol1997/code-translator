package com.example.codetranslator.service;

import com.example.codetranslator.domain.UserRequest;
import com.example.codetranslator.entity.ExceptionEntity;
import com.example.codetranslator.repository.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class TranslateService {

    @Autowired
    private ExceptionRepository exceptionRepository;

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

        try{

            return restTemplate.postForEntity(url, request, String.class);

        }catch (Exception e){

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                    .requestCode(userRequest.getRequestCode())
                    .language(userRequest.getLanguage() != "" ? userRequest.getLanguage() : "auto")
                    .targetLanguage(userRequest.getTargetLanguage())
                    .errorMessage(e.getMessage())
                    .stackTrace(sw.toString())
                    .build();

            System.out.println(exceptionEntity);

            exceptionRepository.save(exceptionEntity);

            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }

}
