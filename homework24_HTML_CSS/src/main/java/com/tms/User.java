package com.tms;

import com.tms.Gender;
import com.tms.Role;

public class User {
    private String login;
    private String password;
    private Gender gender;
    private String description;
    private Role role;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Gender gender, String description, Role role) {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.description = description;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", role=" + role +
                '}';
    }
}
