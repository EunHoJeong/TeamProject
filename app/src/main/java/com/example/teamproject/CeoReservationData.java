package com.example.teamproject;

import java.util.HashMap;
import java.util.Map;

public class CeoReservationData {
    private String id;
    private String roomName;
    private String roomData;

    public CeoReservationData(){}

    public CeoReservationData(String id, String roomName, String roomData) {
        this.id = id;
        this.roomName = roomName;
        this.roomData = roomData;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomData() {
        return roomData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("roomName", roomName);
        result.put("roomData", roomData);

        return result;
    }

}
