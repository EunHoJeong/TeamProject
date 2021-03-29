package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class StoreInfo {
    private String storeName;
    private String location;
    private String phone;
    private int grade;
    private int gradeCount;
    private int review;
    private String location_tag;
    private String mainImage;

    public StoreInfo(){}

    public StoreInfo(String storeName, String location, String phone, int grade, int gradeCount, int review, String location_tag, String mainImage) {
        this.storeName = storeName;
        this.location = location;
        this.phone = phone;
        this.grade = grade;
        this.gradeCount = gradeCount;
        this.review = review;
        this.location_tag = location_tag;
        this.mainImage = mainImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public int getGrade() {
        return grade;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public int getReview() {
        return review;
    }

    public String getLocation_tag() {
        return location_tag;
    }

    public String getMainImage() {
        return mainImage;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("location", location);
        result.put("phone", phone);
        result.put("grade", grade);
        result.put("gradeCount", gradeCount);
        result.put("review", review);
        result.put("location_tag", location_tag);

        return result;
    }
}
