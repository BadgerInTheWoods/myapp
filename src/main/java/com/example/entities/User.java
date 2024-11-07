package com.example.entities;

import java.time.LocalDate;
import java.time.Period;

public class User {

    private int id;
    private String name;
    private LocalDate dateofbirth;
    private String gender;

    // Constructor
    public User(int id, String name, LocalDate dateofbirth, String gender) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for dateofbirth
    public LocalDate getDateOfBirth() {
        return dateofbirth;
    }

    // Setter for dateofbirth
    public void setDateOfBirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateofbirth, currentDate);
        return period.getYears();
    }

    // Override toString method for easy representation
    @Override
    public String toString() {
        return "id=" + id
                + ", name=" + name
                + ", date of birth=" + dateofbirth
                + ", age=" + calculateAge()
                + ", gender=" + gender;
    }

}
