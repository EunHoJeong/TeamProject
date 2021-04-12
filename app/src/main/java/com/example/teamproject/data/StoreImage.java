package com.example.teamproject.data;

import java.util.HashMap;
import java.util.Map;

public class StoreImage {
    private String storeName;
    private String st1;
    private String st2;
    private String st3;
    private String sp1;
    private String sp2;
    private String sp3;
    private String sw1;
    private String sw2;
    private String sw3;

    public StoreImage(){}

    public StoreImage(String storeName, String st1, String st2, String st3, String sp1, String sp2, String sp3, String sw1, String sw2, String sw3) {
        this.storeName = storeName;
        this.st1 = st1;
        this.st2 = st2;
        this.st3 = st3;
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.sp3 = sp3;
        this.sw1 = sw1;
        this.sw2 = sw2;
        this.sw3 = sw3;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSt1() {
        return st1;
    }

    public String getSt2() {
        return st2;
    }

    public String getSt3() {
        return st3;
    }

    public String getSp1() {
        return sp1;
    }

    public String getSp2() {
        return sp2;
    }

    public String getSp3() {
        return sp3;
    }

    public String getSw1() {
        return sw1;
    }

    public String getSw2() {
        return sw2;
    }

    public String getSw3() {
        return sw3;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("storeName", storeName);
        result.put("st1", st1);
        result.put("st2", st2);
        result.put("st3", st3);
        result.put("sp1", sp1);
        result.put("sp2", sp2);
        result.put("sp3", sp3);
        result.put("sw1", sw1);
        result.put("sw2", sw2);
        result.put("sw3", sw3);

        return result;
    }
}
