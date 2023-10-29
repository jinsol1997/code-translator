package com.example.codetranslator.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String requestCode;
    private String language;

    private String targetLanguage;

    public String toJson() {

        Gson gson = new Gson();
        JsonObject jsonRequest = new JsonObject();

        jsonRequest.addProperty("max_tokens", 1000);
        jsonRequest.addProperty("model", "gpt-3.5-turbo");

        JsonArray messages = new JsonArray();
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "You are a code converter assistant.");
        messages.add(systemMessage);

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", "Convert this "+ language +" code to " + targetLanguage + ":");
        messages.add(userMessage);

        JsonObject userCodeMessage = new JsonObject();
        userCodeMessage.addProperty("role", "user");
        userCodeMessage.addProperty("content", requestCode);
        messages.add(userCodeMessage);

        // Add messages array to the request
        jsonRequest.add("messages", messages);

        return gson.toJson(jsonRequest);
    }

}
