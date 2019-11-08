package com.example.take.some.rest.springrest.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApiModel(description = "A description for the user")
public class User {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private int id;

    @Size(min=5, max = 100, message = "Name must be between 5 and 100 characters.")
    @Pattern(regexp = "[a-z][A-Z]", message = "Name must only contain alphanumeric characters.")
    private String name;

    @Past
    @ApiModelProperty(notes="Birth date cannot be in the past")
    private LocalDate birthDate;

    private User() { }

    public User(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.from(dtf.parse(birthDate));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
