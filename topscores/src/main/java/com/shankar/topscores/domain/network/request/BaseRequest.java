package com.shankar.topscores.domain.network.request;

import com.shankar.topscores.config.BaseRequestHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class BaseRequest {
    protected HttpServletRequest httpRequest;
    protected BaseRequestHelper baseRequestHelper;
    protected Map<String, String[]> requestParameters;


    public BaseRequest(HttpServletRequest httpRequest, BaseRequestHelper baseRequestHelper) {
        this.httpRequest = httpRequest;
        this.baseRequestHelper = baseRequestHelper;
    }

    private void setBaseParameters() {
        this.requestParameters = httpRequest.getParameterMap();
    }

    public String getParam(String key) {
        try {
            return (isParamPresent(key)) ? this.requestParameters.get(key)[0] : null;
        } catch (Exception e) {

            return null;
        }
    }

    public boolean isParamPresent(String key) {
        return this.requestParameters.containsKey(key);
    }

}
