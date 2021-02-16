package com.codefororlando.fyp.model;

public class UserPet {

    private String name;
    private String dog;
    private String breed;
    private String description;
    private String number;
    private String email;




    public UserPet(String name, String dog, String dBreed, String dDesc, String cNumber, String email) {
        this.name = name;
        this.dog = dog;
        this.breed = dBreed;
        this.description = dDesc;
        this.number = cNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDogname() {
        return dog;
    }

    public void setDogname(String dogname) {
        this.dog = dogname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
