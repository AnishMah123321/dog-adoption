package com.codefororlando.fyp.model;

public class UserResponse {
    private Boolean success;
    private String message;
    private UserPet userPet;

    public UserResponse(Boolean success, String message, UserPet userPet) {
        this.success = success;
        this.message = message;
        this.userPet = userPet;
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

    public UserPet getUserPet() {
        return userPet;
    }

    public void setUserPet(UserPet userPet) {
        this.userPet = userPet;
    }
}
