package com.example.codetranslator.controller;

import com.example.codetranslator.domain.UserRequest;
import com.example.codetranslator.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @PostMapping("translate")
    public ResponseEntity<String> translate(@RequestBody UserRequest userRequest){

        ResponseEntity<String> responseEntity = translateService.translate(userRequest);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
           return responseEntity;
        }

        return new ResponseEntity<>("Translation failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
