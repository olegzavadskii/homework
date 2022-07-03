package com.tms.entity;

import com.tms.Gender;
import com.tms.Role;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DBUser {
    private List<User> dbUserList;

    public DBUser(List<User> dbUserList) {
        this.dbUserList = dbUserList;
    }

    public void addUser(User user) {
        dbUserList.add(user);
    }

    public long checkWithRole(String login, String password) {
        return dbUserList.stream()
                .filter(user -> Role.ADMIN.equals(user.getRole()))
                .filter(user -> login.equals(user.getLogin()))
                .filter(user -> password.equals(user.getPassword()))
                .count();
    }

    public long checkWithLogin(String login) {
        return dbUserList.stream()
                .filter(user -> login.equals(user.getLogin()))
                .count();
    }

}
