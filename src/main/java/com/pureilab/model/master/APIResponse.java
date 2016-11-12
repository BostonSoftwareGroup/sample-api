package com.pureilab.model.master;

/**
 * Created by Julian on 10/31/2016.
 */
public class APIResponse {

    private String code = "";

    private String message = "";

    private Object body = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
