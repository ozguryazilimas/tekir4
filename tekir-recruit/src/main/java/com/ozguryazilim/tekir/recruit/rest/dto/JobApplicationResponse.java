package com.ozguryazilim.tekir.recruit.rest.dto;

public class JobApplicationResponse{
    private String token;

    public JobApplicationResponse() {
    }

    public JobApplicationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
