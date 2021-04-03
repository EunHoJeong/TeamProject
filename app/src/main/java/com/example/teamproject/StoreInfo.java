package com.example.teamproject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StoreInfo implements Serializable {
    private String storeName;
    private String location;
    private String phone;
    private float grade;
    private int gradeCount;
    private int review;
    private String location_tag;
    private String mainImage;
    private String st_Large;
    private String st_Lodgment;
    private String st_Time1;
    private String st_Time2;

    public StoreInfo(){}

    public StoreInfo(String storeName, String location, String phone, float grade, int gradeCount, int review, String location_tag, String mainImage, String st_Large, String st_Lodgment, String st_Time1, String st_Time2) {
        this.storeName = storeName;
        this.location = location;
        this.phone = phone;
        this.grade = grade;
        this.gradeCount = gradeCount;
        this.review = review;
        this.location_tag = location_tag;
        this.mainImage = mainImage;
        this.st_Large = st_Large;
        this.st_Lodgment = st_Lodgment;
        this.st_Time1 = st_Time1;
        this.st_Time2 = st_Time2;
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

    public float getGrade() {
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

    public String getSt_Large() {
        return st_Large;
    }

    public String getSt_Lodgment() {
        return st_Lodgment;
    }

    public String getSt_Time1() {
        return st_Time1;
    }

    public String getSt_Time2() {
        return st_Time2;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }

    public void setReview(int review) {
        this.review = review;
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
        result.put("mainImage", mainImage);
        result.put("st_Large", st_Large);
        result.put("st_Lodgment", st_Lodgment);
        result.put("st_Time1", st_Time1);
        result.put("st_Time2", st_Time2);

        return result;
    }
}
