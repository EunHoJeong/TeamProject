package com.example.teamproject.data;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String password;
    private String email;
    private String phone;

    public User(){}

    public User(String id, String password, String email, String phone) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("password", password);
        result.put("email", email);
        result.put("phone", phone);

        return result;
    }
}
