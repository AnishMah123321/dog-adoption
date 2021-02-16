package com.codefororlando.fyp.model;

public class EmailList {
    private String titleShelter;
    private String emailShelter;

    public EmailList(String titleShelter, String emailShelter) {
        this.titleShelter = titleShelter;
        this.emailShelter = emailShelter;
    }

    public String getTitleShelter() {
        return titleShelter;
    }

    public void setTitleShelter(String titleShelter) {
        this.titleShelter = titleShelter;
    }

    public String getEmailShelter() {
        return emailShelter;
    }

    public void setEmailShelter(String emailShelter) {
        this.emailShelter = emailShelter;
    }
}
