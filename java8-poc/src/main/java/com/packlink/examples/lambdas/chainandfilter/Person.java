package com.packlink.examples.lambdas.chainandfilter;

import java.time.LocalDate;

public class Person {

    public enum Gender {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    String email;
    String phone;
    Gender gender;
    String givenName;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurName() {

        return surName;
    }

    public void setSurName(String surName) {

        this.surName = surName;
    }

    public String getGivenName() {

        return givenName;
    }

    public void setGivenName(String givenName) {

        this.givenName = givenName;
    }

    String surName;

    String emailAddress;

    public int getAge() {
        //TODO
        return 0;
    }

    public Gender getGender() {

        return gender;
    }

    public String getEmailAddress() {

        return emailAddress;
    }

    public void printPerson() {
        //TODO
    }

    public void printWesternName() {

        System.out.println("\nName: " + this.getGivenName() + " " + this.getSurName() + "\n" +
                "Age: " + this.getAge() + "  " + "Gender: " + this.getGender() + "\n");
    }

    public void printEasternName() {

        System.out.println("\nName: " + this.getSurName() + " " + this.getGivenName() + "\n" +
                "Age: " + this.getAge() + "  " + "Gender: " + this.getGender() + "\n");
    }

}
