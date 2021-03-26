package com.example.teamproject;

public class ReservationConfirmData {
    private String state;
    private String storeName;
    private String roomInfo;
    private String date;

    public ReservationConfirmData(String state, String storeName, String roomInfo, String date) {
        this.state = state;
        this.storeName = storeName;
        this.roomInfo = roomInfo;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public String getDate() {
        return date;
    }
}
