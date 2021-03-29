package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class StorePrice {
    private String storeName;
    private String st_Large;
    private String st_Lodgment;
    private String sp_Large;
    private String sp_Lodgment;
    private String sw_Large;
    private String sw_Lodgment;

    public StorePrice(){}

    public StorePrice(String storeName, String st_Large, String st_Lodgment, String sp_Large, String sp_Lodgment, String sw_Large, String sw_Lodgment) {
        this.storeName = storeName;
        this.st_Large = st_Large;
        this.st_Lodgment = st_Lodgment;
        this.sp_Large = sp_Large;
        this.sp_Lodgment = sp_Lodgment;
        this.sw_Large = sw_Large;
        this.sw_Lodgment = sw_Lodgment;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("storeName", storeName);
        result.put("st_Large", st_Large);
        result.put("st_Lodgment", st_Lodgment);
        result.put("sp_Large", sp_Large);
        result.put("sp_Lodgment", sp_Lodgment);
        result.put("sw_Large", sw_Large);
        result.put("sw_Lodgment", sw_Lodgment);

        return result;
    }
}
