package com.codefororlando.fyp.model;

public class ServerResponse {
    private Boolean success;
    private String message;
    private User user;


    public ServerResponse(Boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;


    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }
}
