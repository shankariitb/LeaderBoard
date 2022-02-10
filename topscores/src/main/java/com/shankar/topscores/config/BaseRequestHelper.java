package com.shankar.topscores.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BaseRequestHelper {
    @Autowired
    private JsonHelper jsonHelper;

    public JsonHelper getJsonToClassHelper(){
        return jsonHelper;
    }

}


