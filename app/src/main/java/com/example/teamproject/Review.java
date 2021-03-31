package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class Review {
    private String storeName;
    private float grade;
    private String id;
    private String contents;

    public Review(){};

    public Review(String storeName, float grade, String id, String contents) {
        this.storeName = storeName;
        this.grade = grade;
        this.id = id;
        this.contents = contents;
    }

    public String getStoreName() {
        return storeName;
    }

    public float getGrade() {
        return grade;
    }

    public String getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("grade", grade);
        result.put("id", id);
        result.put("contents", contents);

        return result;
    }

}
