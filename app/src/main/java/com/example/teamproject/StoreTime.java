package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class StoreTime {
    private String storeName;
    private String st_Time1;
    private String st_Time2;
    private String sp_Time1;
    private String sp_Time2;
    private String sw_Time1;
    private String sw_Time2;

    public StoreTime(){}

    public StoreTime(String storeName, String st_Time1, String st_Time2, String sp_Time1, String sp_Time2, String sw_Time1, String sw_Time2) {
        this.storeName = storeName;
        this.st_Time1 = st_Time1;
        this.st_Time2 = st_Time2;
        this.sp_Time1 = sp_Time1;
        this.sp_Time2 = sp_Time2;
        this.sw_Time1 = sw_Time1;
        this.sw_Time2 = sw_Time2;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("st_Time1", st_Time1);
        result.put("st_Time2", st_Time2);
        result.put("sp_Time1", sp_Time1);
        result.put("sp_Time2", sp_Time2);
        result.put("sw_Time1", sw_Time1);
        result.put("sw_Time2", sw_Time2);

        return result;
    }
}
