package com.packlink.examples.lambdas.person;

import java.time.LocalDate;

public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;

    String emailAddress;

    public int getAge() {
        //TODO
        return 0;
    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void printPerson() {
        //TODO
    }

}
