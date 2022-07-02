package com.tms.entity;

import com.tms.Gender;
import com.tms.Role;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DBUser {
    private List<User> dbUserList;

    public DBUser(List<User> dbUserList) {
        this.dbUserList = dbUserList;
    }

    public void addUser(User user) {
        dbUserList.add(user);
    }

    public Role checkWithRole(User user) {
        Role role = Role.USER;
        Iterator<User> iterator = dbUserList.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getLogin().equals(user.getLogin())) {
                if (next.getPassword().equals(user.getPassword())) {
                    role = next.getRole();
                }
            }
        }
        return role;
    }

    public boolean checkWithLogin(User user) {
        boolean isFind = false;
        Iterator<User> iterator = dbUserList.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getLogin().equalsIgnoreCase(user.getLogin())) {
                isFind = true;
            }
        }
        return isFind;
    }

}
