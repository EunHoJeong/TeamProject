package com.example.teamproject.data;

import java.util.HashMap;
import java.util.Map;

public class StorePrice {
    private String storeName;
    private String sp_Large;
    private String sp_Lodgment;
    private String sw_Large;
    private String sw_Lodgment;

    public StorePrice(){}

    public StorePrice(String storeName, String sp_Large, String sp_Lodgment, String sw_Large, String sw_Lodgment) {
        this.storeName = storeName;
        this.sp_Large = sp_Large;
        this.sp_Lodgment = sp_Lodgment;
        this.sw_Large = sw_Large;
        this.sw_Lodgment = sw_Lodgment;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSp_Large() {
        return sp_Large;
    }

    public String getSp_Lodgment() {
        return sp_Lodgment;
    }

    public String getSw_Large() {
        return sw_Large;
    }

    public String getSw_Lodgment() {
        return sw_Lodgment;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("sp_Large", sp_Large);
        result.put("sp_Lodgment", sp_Lodgment);
        result.put("sw_Large", sw_Large);
        result.put("sw_Lodgment", sw_Lodgment);

        return result;
    }
}
