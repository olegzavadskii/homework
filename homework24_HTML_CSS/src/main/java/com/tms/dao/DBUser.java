package com.tms.dao;

import com.tms.Gender;
import com.tms.Role;
import com.tms.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DBUser {

    User admin1 = new User("admin1", "123", Gender.MALE, "I am an admin", Role.ADMIN);
    User user1 = new User("user1", "321", Gender.FEMALE, "I am a user", Role.USER);

    List<User> dbUserList = new ArrayList<>();

    /*блок инициализации для листа-мапы*/ {
        dbUserList.add(admin1);
        dbUserList.add(user1);
    }

    private static DBUser dbUser;

    private DBUser() {

    }

    public static DBUser getDbUser() {
        if (dbUser != null) {
            return dbUser;
        } else {
            synchronized (DBUser.class) {
                if (dbUser == null) {
                    dbUser = new DBUser();
                }
            }
        }
        return dbUser;
    }

    public DBUser(List<User> dbUserList) {
        this.dbUserList = dbUserList;
    }

    public void addUser(User user) {
        dbUserList.add(user);
    }

    public boolean checkWithRole(String login, String password) {
        return dbUserList.stream()
                .filter(user -> Role.ADMIN.equals(user.getRole()))
                .filter(user -> login.equals(user.getLogin()))
                .anyMatch(user -> password.equals(user.getPassword()));
    }

    public boolean checkWithLogin(String login) {
        return dbUserList.stream()
                .anyMatch(user -> login.equals(user.getLogin()));
    }

}
