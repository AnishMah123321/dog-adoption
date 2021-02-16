package com.codefororlando.fyp.model;

public class User {

    private String username;
    private String password;
    private String email;
    private String mobile;
    private String repassword;

    public User(String username, String password, String email, String mobile, String repassword){
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.repassword = repassword;
    }

    public User(String email, String password) {
        this.email=email;
        this.password=password;
    }

    public User(String name, String email, String contact) {
        this.username = name;
        this.email = email;
        this.mobile = contact;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
