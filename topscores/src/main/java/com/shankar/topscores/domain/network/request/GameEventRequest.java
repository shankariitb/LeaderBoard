package com.shankar.topscores.domain.network.request;

import com.shankar.topscores.config.BaseRequestHelper;

import javax.servlet.http.HttpServletRequest;

public class GameEventRequest extends  BaseRequest{

    public GameEventRequest(HttpServletRequest httpRequest, BaseRequestHelper baseRequestHelper) {
        super(httpRequest, baseRequestHelper);
    }

    public <T> T getData(Class<T> clazz) throws Exception {
        return (T) baseRequestHelper.getJsonToClassHelper().fromJson(getParam("data"), clazz);
    }
}
