package com.example.agenda;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class InfoUser {

    private static final InfoUser INSTANCE = new InfoUser();
    private Map<String, String> usersDB;

    public static InfoUser getInstance() {
        return INSTANCE;
    }

    private InfoUser() {
        this.usersDB = new HashMap<String, String>();
    }

    public void addUser(String username, String password){
        usersDB.put(username, password);
    }

    public boolean checkUser(String username, String password){
        if (usersDB.containsKey(username) && usersDB.containsValue(password)) {
            return true;
        }
        return false;

    }
}
