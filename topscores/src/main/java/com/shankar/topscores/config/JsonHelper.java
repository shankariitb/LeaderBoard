package com.shankar.topscores.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonHelper {
    @Autowired
    private ObjectMapper objectMapper;

    public <T> String toJson(T toMarshal) {
        try {
            return objectMapper.writeValueAsString(toMarshal);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) throws Exception {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw e;
        }
    }
}