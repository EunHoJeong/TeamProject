package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class StoreTime {
    private String storeName;
    private String sp_Time1;
    private String sp_Time2;
    private String sw_Time1;
    private String sw_Time2;

    public StoreTime(){}

    public StoreTime(String storeName, String sp_Time1, String sp_Time2, String sw_Time1, String sw_Time2) {
        this.storeName = storeName;
        this.sp_Time1 = sp_Time1;
        this.sp_Time2 = sp_Time2;
        this.sw_Time1 = sw_Time1;
        this.sw_Time2 = sw_Time2;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSp_Time1() {
        return sp_Time1;
    }

    public String getSp_Time2() {
        return sp_Time2;
    }

    public String getSw_Time1() {
        return sw_Time1;
    }

    public String getSw_Time2() {
        return sw_Time2;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("sp_Time1", sp_Time1);
        result.put("sp_Time2", sp_Time2);
        result.put("sw_Time1", sw_Time1);
        result.put("sw_Time2", sw_Time2);

        return result;
    }
}
