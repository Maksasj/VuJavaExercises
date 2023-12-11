package com.example.tableviewer;

import com.example.tableviewer.utils.Utils;

import java.time.LocalDate;
import java.util.Date;

public class Human {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public String country;
    public String domain;
    public LocalDate birthDate;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getDomain() {
        return domain;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private Human(int id, String firstName, String lastName, String email, String gender, String country, String domain, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.domain = domain;
        this.birthDate = birthDate;
    }

    public static Human loadFromString(String input) {
        String[] parts = Utils.parseCSV(input);

        if(parts.length != 8)
            throw new RuntimeException("Failed to parse input string");

        var id = Integer.valueOf(parts[0]);
        var firstName = parts[1];
        var lastName = parts[2];
        var email = parts[3];
        var gender = parts[4];
        var country = parts[5];
        var domain = parts[6];
        var birthDate = LocalDate.parse(parts[7]);

        return new Human(id, firstName, lastName, email, gender, country, domain, birthDate);
    }
}
